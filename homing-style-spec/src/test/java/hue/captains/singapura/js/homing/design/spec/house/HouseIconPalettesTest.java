package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.SpecRegistry;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.IconContent;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's icon palettes (RFC 0067, D56): every icon its picture in every style - a glyph, its style's own or base's,
 * or for an SVG none, the page drawing a placeholder; each glyph the one today's sheet of that style drew on the icon's
 * ::before, moved unchanged; a palette refused that leaves an icon without a picture, draws one no icon shows, or is a
 * style's twice; and the page's module, one object a picture, shared by the styles that draw it alike.
 */
class HouseIconPalettesTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final StyleManager TODAY = HouseStyles.INSTANCE.styling().manager();
    private static final IconPictures PICTURES = HouseIconPalettes.INSTANCE.read(SPEC.taxonomy());
    private static final Pattern GLYPH = Pattern.compile("content:\\s*\"([^\"]*)\"");

    @Test
    void everyIcon_hasItsPicture_inEveryStyle() {
        assertEquals(20, PICTURES.icons().size());
        for (DesignStyle<?> s : SPEC.styles())
            for (IconLeaf i : PICTURES.icons())
                assertEquals(i.content() instanceof IconContent.Glyph, PICTURES.glyph(s, i).isPresent(), s.token() + " " + i.token());
    }

    @Test
    void eachGlyph_isTheOneTodaysSheetDrew_inThatStyle() throws IOException {
        var wrong = new ArrayList<String>();
        int checked = 0;
        for (DesignStyle<?> s : SPEC.styles())
            for (IconLeaf i : PICTURES.icons()) {
                if (!(i.content() instanceof IconContent.Glyph)) continue;
                String sheet = sheet(TODAY.resolve(s, new Casting(i)).css().getClass().getName());
                Matcher m = GLYPH.matcher(sheet);
                assertTrue(m.find(), s.token() + " " + i.token() + ": today's sheet draws no glyph");
                String now = PICTURES.glyph(s, i).orElseThrow();
                if (!m.group(1).equals(now)) wrong.add(s.token() + " " + i.token() + ": today " + m.group(1) + ", its palette " + now);
                checked++;
            }
        System.out.println("[icon-palettes] " + checked + " glyphs, each the one today's sheet drew");
        assertEquals(List.of(), wrong);
    }

    @Test
    void aStyleDraws_onlyWhereItDiffers() {
        IconLeaf check = PICTURES.icons().stream().filter(i -> i.content().name().equals("check")).findFirst().orElseThrow();
        IconLeaf add = PICTURES.icons().stream().filter(i -> i.content().name().equals("add")).findFirst().orElseThrow();
        assertEquals("✔", PICTURES.glyph(Sketchy.INSTANCE, check).orElseThrow());
        assertTrue(PICTURES.own(Sketchy.INSTANCE, check));
        assertEquals("+", PICTURES.glyph(Sketchy.INSTANCE, add).orElseThrow(), "sketchy draws base's plus");
        assertTrue(!PICTURES.own(Sketchy.INSTANCE, add));
    }

    @Test
    void aPalette_isRefused_thatLeavesAnIconWithoutAPicture_drawsWhatNoIconShows_orIsAStylesTwice() {
        var base = HouseIconPalettes.INSTANCE.base();
        var less = new java.util.LinkedHashMap<>(base.glyphs());
        less.remove(new IconContent.Glyph("check"));
        assertThrows(IllegalArgumentException.class, () -> new IconPictures(PICTURES.icons(), new IconPalette(Base.INSTANCE, less), List.of()));
        assertThrows(IllegalArgumentException.class, () -> new IconPictures(PICTURES.icons(), base,
                List.of(new IconPalette(Sketchy.INSTANCE, Map.of(new IconContent.Glyph("nothing-shown"), "?")))));
        assertThrows(IllegalArgumentException.class, () -> new IconPictures(PICTURES.icons(), base,
                List.of(new IconPalette(Sketchy.INSTANCE, Map.of()), new IconPalette(Sketchy.INSTANCE, Map.of()))));
        assertThrows(IllegalArgumentException.class, () -> new IconPalette(Sketchy.INSTANCE, Map.of(new IconContent.Glyph("check"), " ")));
    }

    @Test
    void thePagesModule_oneObjectAPicture_sharedByTheStylesThatDrawItAlike() {
        String js = IconPicturesScript.INSTANCE.script(PICTURES, SPEC.selectable());
        assertEquals(7, count(js, "const GLYPH_CLOSE"), "base's cross, and five styles' and sketchy's own");
        assertEquals(1, count(js, "const GLYPH_ADD "), "one plus, for every style");
        assertEquals(1, count(js, "const SVG_ROTATE "), "the samples: one each, a placeholder in every style");
        assertEquals(SPEC.selectable().size(), count(js, ", new Map(["));
        assertTrue(js.contains("export { iconPictures };"));
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
