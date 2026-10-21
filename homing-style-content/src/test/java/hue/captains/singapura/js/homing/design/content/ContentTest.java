package hue.captains.singapura.js.homing.design.content;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.semantic.ColorTree;
import hue.captains.singapura.js.homing.design.spec.CheckContent;
import hue.captains.singapura.js.homing.design.spec.CssResolution;
import hue.captains.singapura.js.homing.design.spec.DuplicateContent;
import hue.captains.singapura.js.homing.design.spec.SpecRegistry;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;
import hue.captains.singapura.js.homing.design.spec.house.HouseIconPalettes;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.spec.house.IconPictures;
import hue.captains.singapura.js.homing.design.spec.house.IconPicturesScript;
import hue.captains.singapura.js.homing.design.spec.house.IconSvg;
import hue.captains.singapura.js.homing.design.spec.house.IconSvgs;
import hue.captains.singapura.js.homing.design.styling.CheckDeclaration;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's content held to the registry (RFC 0067, D53): every implementation with CSS of its own has its content,
 * read and held whole as core holds an implementation's CSS - its marks, its casting's rule, its colours both ways; every
 * SVG picture here held whole, its colours the palette's (D56); the jar ships nothing else; and the classpath put
 * together here gives no content twice.
 */
class ContentTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final ColorTree TREE = BaseColours.TREE;
    private static final Path SHIPPED = Path.of("src/main/resources");
    private static final IconPictures PICTURES = HouseIconPalettes.INSTANCE.read(SPEC.taxonomy());

    @Test
    void everyImplementation_hasItsContent_heldWhole() {
        List<String> problems = CheckContent.INSTANCE.content(SPEC, TREE);
        assertTrue(problems.isEmpty(), problems.size() + " problems:\n" + String.join("\n", problems.subList(0, Math.min(40, problems.size()))));
        System.out.println("[content] " + SPEC.implementations().size() + " implementations: each has its content - marks, rule, colours both ways");
    }

    /** The check bites: an implementation's content held with one colour it uses left off its list is refused for it. */
    @Test
    void aColourUsedAndNotListed_isRefused() {
        SpecRegistry.Worn end = SPEC.implementations().stream().filter(e -> !e.impl().colours().isEmpty()).findFirst().orElseThrow();
        var found = (CssResolution.Found) SPEC.content(end.impl());
        List<? extends ColorLeaf<?, ?, ?>> listed = end.impl().colours();
        List<String> held = CheckDeclaration.INSTANCE.content(SPEC.taxonomy(), TREE, end.layer(), new Casting(end.component()), found.resource(), found.text(), listed);
        assertEquals(List.of(), held);
        List<String> short1 = CheckDeclaration.INSTANCE.content(SPEC.taxonomy(), TREE, end.layer(), new Casting(end.component()), found.resource(), found.text(),
                listed.subList(1, listed.size()));
        assertTrue(short1.stream().anyMatch(p -> p.contains("uses " + listed.getFirst().token() + ", which it does not import")), short1.toString());
    }

    @Test
    void theJar_shipsOnlyContent() throws IOException {
        List<String> shipped;
        try (Stream<Path> walk = Files.walk(SHIPPED)) {
            shipped = walk.filter(Files::isRegularFile).map(p -> SHIPPED.relativize(p).toString().replace('\\', '/')).sorted().toList();
        }
        List<String> sheets = shipped.stream().filter(p -> !p.startsWith(IconSvgs.PREFIX)).toList();
        List<String> svgs = shipped.stream().filter(p -> p.startsWith(IconSvgs.PREFIX)).toList();
        List<String> problems = CheckContent.INSTANCE.leftOver(SPEC, sheets);
        assertTrue(problems.isEmpty(), problems.size() + " left over:\n" + String.join("\n", problems.subList(0, Math.min(40, problems.size()))));
        assertEquals(CheckContent.INSTANCE.expected(SPEC).size(), sheets.size(), "one file for each implementation with CSS of its own");
        List<String> pictures = IconSvgs.INSTANCE.expected(PICTURES, SPEC.styles());
        assertEquals(List.of(), svgs.stream().filter(p -> !pictures.contains(p)).toList(), "every SVG shipped the picture of an icon some palette draws");
        System.out.println("[content] " + shipped.size() + " files shipped - " + sheets.size() + " sheets, " + svgs.size() + " SVG pictures - none left over");
    }

    /**
     * Every SVG picture a palette draws whose resource is here is held whole - shapes only, each saying its fill and its
     * stroke, none or a palette's colour - and each colour it names is a palettable colour its style's scheme sees; the one
     * not here yet is flip's, drawn as its placeholder; and the page's module carries rotate's markup as it is.
     */
    @Test
    void everySvgPicture_isHeldWhole_itsColoursThePalettes() {
        assertEquals(List.of(), IconSvgs.INSTANCE.problems(PICTURES, SPEC.styles(), TREE));
        IconSvg rotate = IconSvgs.INSTANCE.find(new hue.captains.singapura.js.homing.design.spec.styles.base.RotateSvg()).orElseThrow();
        String flip = IconSvgs.INSTANCE.resource(new hue.captains.singapura.js.homing.design.spec.styles.base.FlipSvg());
        IconSvg pacman = IconSvgs.INSTANCE.find(new hue.captains.singapura.js.homing.design.spec.styles.base.PacmanSvg()).orElseThrow();
        IconSvg sketchy = IconSvgs.INSTANCE.find(new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PacmanSvg()).orElseThrow();
        IconSvg preferences = IconSvgs.INSTANCE.find(new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesSvg()).orElseThrow();
        assertEquals(java.util.Set.of(rotate.resource(), flip, pacman.resource(), sketchy.resource(), preferences.resource()), java.util.Set.copyOf(IconSvgs.INSTANCE.expected(PICTURES, SPEC.styles())),
                "base's four, and sketchy's own pacman");
        assertEquals(List.of("accent"), preferences.colours(), "the sliders' knobs; their lines and rings in the colour it is given");
        assertEquals(List.of(flip), IconSvgs.INSTANCE.awaiting(PICTURES, SPEC.styles()), "flip's not here yet: drawn as its placeholder");
        assertEquals(List.of("accent"), rotate.colours(), "its head in the accent; its arc in the colour it is given");
        assertEquals(List.of("accent", "danger-ink"), pacman.colours(), "its body, its pellets; its eye in the colour it is given");
        assertEquals(List.of("accent", "success-ink", "danger-ink"), sketchy.colours(), "its body, its trail, its big pellet; its outline and eye in the colour it is given");
        String js = IconPicturesScript.INSTANCE.script(PICTURES, SPEC.selectable());
        assertTrue(js.contains("svg: \"rotate\", markup: " + JsLiterals.INSTANCE.string(rotate.markup())), "rotate's markup, as it is");
        assertTrue(js.contains("svg: \"pacman\", markup: " + JsLiterals.INSTANCE.string(pacman.markup())), "base's pacman, as it is");
        assertTrue(js.contains("svg: \"pacman\", markup: " + JsLiterals.INSTANCE.string(sketchy.markup())), "sketchy's own, as it is");
        assertTrue(js.contains("Object.freeze({ svg: \"flip\" })"), "flip's name alone");
        System.out.println("[content] " + IconSvgs.INSTANCE.drawn(PICTURES, SPEC.styles()).size() + " SVG pictures drawn: rotate's held whole, naming "
                + rotate.colours() + "; flip's awaiting its resource");
    }

    @Test
    void theClasspath_givesNoContentTwice() {
        assertEquals(List.of(), DuplicateContent.INSTANCE.find(SPEC, getClass().getClassLoader()));
    }
}
