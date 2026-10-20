package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.SpecRegistry;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.IconLeaf;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's icon palettes (RFC 0067, D56): every icon its picture in every style - its style's own, or base's: a
 * character, or for rotate and flip an SVG, the page drawing a placeholder; each character the one today's sheet of that
 * style drew on the icon's ::before, moved unchanged; a palette refused that leaves an icon without a picture, draws one
 * that is no icon, or is a style's twice; and the page's module, one object a picture, shared by the styles that draw it
 * alike.
 */
class HouseIconPalettesTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final StyleManager TODAY = HouseStyles.INSTANCE.styling().manager();
    private static final IconPictures PICTURES = HouseIconPalettes.INSTANCE.read(SPEC.taxonomy());
    private static final Pattern GLYPH = Pattern.compile("content:\\s*\"([^\"]*)\"");

    @Test
    void everyIcon_hasItsPicture_inEveryStyle_theSamplesSvgs() {
        assertEquals(20, PICTURES.icons().size());
        for (DesignStyle<?> s : SPEC.styles())
            for (IconLeaf i : PICTURES.icons()) {
                boolean sample = i.equals(HouseIcons.RotateIcon.INSTANCE) || i.equals(HouseIcons.FlipIcon.INSTANCE);
                assertEquals(sample, PICTURES.picture(s, i) instanceof IconPicture.Svg, s.token() + " " + i.token());
            }
    }

    @Test
    void eachCharacter_isTheOneTodaysSheetDrew_inThatStyle() throws IOException {
        var wrong = new ArrayList<String>();
        int checked = 0;
        for (DesignStyle<?> s : SPEC.styles())
            for (IconLeaf i : PICTURES.icons()) {
                if (!(PICTURES.picture(s, i) instanceof IconPicture.Glyph now)) continue;
                String sheet = sheet(TODAY.resolve(s, new Casting(i)).css().getClass().getName());
                Matcher m = GLYPH.matcher(sheet);
                assertTrue(m.find(), s.token() + " " + i.token() + ": today's sheet draws no glyph");
                if (!m.group(1).equals(now.character())) wrong.add(s.token() + " " + i.token() + ": today " + m.group(1) + ", its palette " + now.character());
                checked++;
            }
        System.out.println("[icon-palettes] " + checked + " characters, each the one today's sheet drew");
        assertEquals(List.of(), wrong);
    }

    @Test
    void aStyleDraws_onlyWhereItDiffers() {
        assertEquals(new IconPicture.Glyph("✔"), PICTURES.picture(Sketchy.INSTANCE, HouseIcons.CheckIcon.INSTANCE));
        assertTrue(PICTURES.own(Sketchy.INSTANCE, HouseIcons.CheckIcon.INSTANCE));
        assertEquals(new IconPicture.Glyph("+"), PICTURES.picture(Sketchy.INSTANCE, HouseIcons.AddIcon.INSTANCE), "sketchy draws base's plus");
        assertFalse(PICTURES.own(Sketchy.INSTANCE, HouseIcons.AddIcon.INSTANCE));
    }

    @Test
    void aPalette_isRefused_thatLeavesAnIconWithoutAPicture_drawsWhatIsNoIcon_orIsAStylesTwice() {
        var base = HouseIconPalettes.INSTANCE.base();
        var less = new java.util.LinkedHashMap<>(base.pictures());
        less.remove(HouseIcons.CheckIcon.INSTANCE);
        assertThrows(IllegalArgumentException.class, () -> new IconPictures(PICTURES.icons(), new IconPalette(Base.INSTANCE, less), List.of()));
        var fewer = PICTURES.icons().stream().filter(i -> !i.equals(HouseIcons.CheckIcon.INSTANCE)).toList();
        assertThrows(IllegalArgumentException.class, () -> new IconPictures(fewer, base, List.of()), "base draws an icon the instance does not register");
        assertThrows(IllegalArgumentException.class, () -> new IconPictures(PICTURES.icons(), base,
                List.of(new IconPalette(Sketchy.INSTANCE, Map.of()), new IconPalette(Sketchy.INSTANCE, Map.of()))));
        assertThrows(IllegalArgumentException.class, () -> new IconPicture.Glyph(" "));
        assertThrows(IllegalArgumentException.class, () -> new IconPicture.Svg("Rotate"));
    }

    @Test
    void thePagesModule_oneObjectAPicture_sharedByTheStylesThatDrawItAlike() {
        String js = IconPicturesScript.INSTANCE.script(PICTURES, SPEC.selectable());
        assertEquals(7, count(js, "const GLYPH_CLOSE_ICON"), "base's cross, and five styles' and sketchy's own");
        assertEquals(1, count(js, "const GLYPH_ADD_ICON "), "one plus, for every style");
        assertEquals(1, count(js, "const SVG_ROTATE_ICON "), "the samples: one each, a placeholder in every style");
        assertEquals(SPEC.selectable().size(), count(js, ", new Map(["));
        assertTrue(js.contains("export { iconPictures };"));
        assertFalse(js.contains("content"), "a picture is no content");
        assertTrue(js.contains("CheckIcon as ComponentCheckIcon") && js.contains("Sketchy as StyleSketchy"), "each node imported by its name in its dual, as its prefixed name");
    }

    private static int count(String text, String what) { return text.split(Pattern.quote(what), -1).length - 1; }

    private static String sheet(String className) throws IOException {
        try (InputStream in = HouseIconPalettesTest.class.getResourceAsStream("/" + className.replace('.', '/') + ".css")) {
            assertNotNull(in, className + ": no sheet on the classpath");
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
