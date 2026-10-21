package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.styling.PaletteSheet;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.ui.styles.BaseColours.BaseColour;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's styles and palettes (RFC 0067, appendix The Style Jars): seven styles over Base, the house's instance held to
 * the central rules - Base implementing every casting, each style drawing the buttons its own way, each sheet whole; the house's colour tree, every
 * palette whole over it, each tint worked out from the palette's seeds; and a page worn in the style and palette its
 * slug names.
 */
class HouseStylesTest {

    private static final Taxonomy TAXONOMY = HouseTaxonomy.INSTANCE.read(HouseTaxonomy.MODULES);

    @Test
    void theStyles_areSeven_eachOverBase_andBaseOverNothing() {
        assertEquals(List.of("editorial", "neo-brutalism", "neo-futurism", "neumorphism", "glassmorphism", "retro-futurism", "sketchy"),
                HouseStyles.STYLES.stream().map(DesignStyle::token).toList(), "the old slugs, Editorial first");
        for (DesignStyle<?> s : HouseStyles.STYLES)
            assertTrue(s instanceof Derives<?, ?> d && d.base() == Base.INSTANCE, s.token() + " is written over Base");
        assertTrue(!Derives.class.isInstance(Base.INSTANCE), "Base is written over nothing: the base layer");
    }

    @Test
    void theHousesInstance_holdsToTheRules_baseImplementsEveryCasting_eachStyleDrawsTheButtons_inEveryRole() {
        StyleManager manager = HouseStyles.INSTANCE.styling().manager();
        assertEquals(TAXONOMY.castings().size(), manager.implementations(Base.INSTANCE).size(), "the base layer is never absent (D26)");
        var go = TAXONOMY.castings().stream().filter(c -> c.token().equals("primary-button_go")).findFirst().orElseThrow();
        for (DesignStyle<?> s : HouseStyles.STYLES) {
            assertEquals(103 + OWN_PICTURES.get(s.token()), manager.implementations(s).size(), s.token() + ": what it refines, its own and the modules' castings, and the icons it draws its own way (D52) - the icon button in add no more, placed in the void role (21 Oct); a section and a rack no more, rooms of their owners; the slider group and its Adjust casting dropped, the panel, its bar and the kicker as a title; the thirteen castings only a widget's role made (20 Oct)");
            for (Component<?> b : BUTTONS)
                assertEquals(s.token() + "'s own", manager.resolve(s, new Casting(b)).says(), s.token() + " draws " + b.token());
            assertTrue(manager.resolve(s, go).says().startsWith(s.token() + "'s own"), s.token() + " draws the primary button as Go too");
            assertTrue(manager.resolve(s, new Casting(HouseMarks.BoundaryLine.INSTANCE)).says().startsWith("base's"),
                    s.token() + ": a boundary is Base's, every style's alike");
            assertTrue(manager.resolve(s, new Casting(HouseMarks.BrandMark.INSTANCE)).says().startsWith("base's"),
                    s.token() + ": the brand's mark is Base's, alike in every style");
        }
    }

    @Test
    void aStylesSheet_aClassForEveryCasting_everyMarkFilledIn() {
        Styling styling = HouseStyles.INSTANCE.styling();
        for (DesignStyle<?> s : HouseStyles.STYLES) {
            String sheet = styling.styleSheet(s.token());
            assertTrue(!sheet.contains("$"), s.token() + ": every mark filled in");
            for (Casting c : TAXONOMY.castings()) assertTrue(sheet.contains("\n.styled-" + c.token() + " {"), s.token() + ": " + c.token());
        }
    }

    /** The buttons every house style draws its own way. */
    private static final List<Component<?>> BUTTONS = List.of(
            HouseControls.PlainButton.INSTANCE, HouseControls.PrimaryButton.INSTANCE, HouseControls.SecondaryButton.INSTANCE,
            HouseControls.DangerButton.INSTANCE, HouseControls.WarningButton.INSTANCE, HouseControls.SuccessButton.INSTANCE,
            HouseControls.ToggleButton.INSTANCE, HouseControls.IconButton.INSTANCE, HouseControls.CloseButton.INSTANCE,
            HouseControls.PreferencesButton.INSTANCE);

    @Test
    void theHouseColourTree_reads_andEveryPaletteIsWholeOverIt() {
        var tree = BaseColours.TREE;
        for (ColorPalette<?> p : HouseStyles.INSTANCE.styling().palettes()) {
            assertEquals(List.of(), tree.check(p), p.token());
            assertEquals(69, PaletteSheet.INSTANCE.write(tree, p).variables().size(), p.token() + ": every palettable colour of the house - the primary's and the secondary's neutral grounds (D41), a floating pane's glows, the ink on a dark bar, a hint of the accent, an edge strip's shades, the inks among them, danger's, warning's, success's and the one on danger the palette's (D32), the text's faded, a heading's and a kicker's, the washes under code, a pill and a badge, the keys' marks, a groove's edge, a tab's washes and shadows, an item's current and pointed, the scrollbar's thumb, a panel's shades and sheen among them");
        }
        assertEquals(8, HouseStyles.INSTANCE.styling().palettes().size(), "the base scheme's jar of palettes, by its declaration");
    }

    @Test
    void aTint_isWorkedOutFromThePalettesSeeds_inSrgb_alphaPremultiplied() {
        var rule = HousePalettes.INSTANCE;
        assertEquals(new ConcreteColor.Srgb("#f4b942"), rule.of(Harbour.SEEDS, new BaseColours.Accent()), "a seed as it is");
        assertEquals(new ConcreteColor.Srgb(17, 25, 54, 0.04).css(), rule.of(Harbour.SEEDS, new BaseColours.ShadeFaint()).css(),
                "the inverted layer at 4%");
        var ring = (ConcreteColor.Srgb) rule.of(Harbour.SEEDS, new BaseColours.Ring());
        assertEquals(List.of(236, 206, 144), List.of(ring.red(), ring.green(), ring.blue()), "the accent leaning 55% into the hairline");
        assertEquals("rgb(255 255 255 / 0.192)", rule.of(Frost.SEEDS, new BaseColours.Sheen()).css(), "glass stays glass: a third white, at 60%");
        assertTrue(Harbour.INSTANCE.color(new BaseColours.Text()).isPresent());
        assertTrue(Harbour.INSTANCE.color(new BaseColours.Text()).get() instanceof ConcreteColor.Srgb);
        BaseColour any = new BaseColours.Wash();
        assertEquals("rgb(59 74 107 / 0.12)", rule.of(Harbour.SEEDS, any).css(), "the text's ink, washed thin");
        assertEquals("rgb(59 74 107 / 0.8)", rule.of(Harbour.SEEDS, new BaseColours.TextFaded()).css(), "the text faded, a way's ink and a caption's: a fifth faded");
        assertEquals(new ConcreteColor.Srgb(45, 57, 102, 1).css(), rule.of(Harbour.SEEDS, new BaseColours.HeadingInk()).css(), "a heading: the navy leaning half into the text");
        assertEquals("rgb(43 55 101 / 0.9)", rule.of(Harbour.SEEDS, new BaseColours.WayHovered()).css(),
                "a way under the hand: the house's navy leaning half into the way's ink, as the old sheets mixed it");
    }

    /** How many icons each style draws its own way: the cross, in all but Editorial; Sketchy five more. */
    private static final Map<String, Integer> OWN_PICTURES = Map.of("editorial", 0, "neo-brutalism", 1, "neo-futurism", 1, "neumorphism", 1,
            "glassmorphism", 1, "retro-futurism", 1, "sketchy", 6);

    @Test
    void everyStyle_drawsEveryIcon_andItsCrossIsItsOwn() {
        StyleManager manager = HouseStyles.INSTANCE.styling().manager();
        var icons = TAXONOMY.components().stream().filter(c -> TAXONOMY.parent(c) == HouseBranches.Icon.INSTANCE).toList();
        assertEquals(21, icons.size(), "the house's sixteen - the pacman among them (21 Oct) - and the panes' five");
        for (DesignStyle<?> s : HouseStyles.STYLES)
            for (Component<?> icon : icons) assertTrue(manager.rule(s, new Casting(icon)).contains("content: \""), s.token() + " draws " + icon.token());
        var crosses = new java.util.LinkedHashMap<String, String>();
        for (DesignStyle<?> s : HouseStyles.STYLES) {
            String rule = manager.rule(s, new Casting(HouseIcons.CloseIcon.INSTANCE));
            int at = rule.indexOf("content: \"") + "content: \"".length();
            crosses.put(s.token(), rule.substring(at, rule.indexOf('"', at)));
        }
        assertEquals(Map.of("editorial", "✕", "neo-brutalism", "╳", "neo-futurism", "⨯", "neumorphism", "✗", "glassmorphism", "×",
                "retro-futurism", "✘", "sketchy", "✖"), crosses, "each style's own cross, the close button's and every close row's alike: the close icon's look");
    }

    @Test
    void aPageIsWornInTheStyleAndPaletteItsSlugNames_theStylesOwnOtherwise() {
        Styling styles = HouseStyles.INSTANCE.styling();
        assertEquals("sketchy in marker", styles.dressed("sketchy").toString(), "a style alone is worn in its own palette");
        assertEquals("glassmorphism in frost", styles.dressed("glassmorphism").toString());
        assertEquals("sketchy in forest", styles.dressed("sketchy_forest").toString());
        assertEquals("glassmorphism in harbour", styles.dressed("glassmorphism_harbour").toString(), "any style in any palette");
        assertEquals("sketchy in marker", styles.dressed("sketchy_sunset").toString(), "a palette the house has not: the style's own");
        assertEquals("editorial in harbour", styles.dressed("flat-morphism").toString(), "retired: the default");
        assertEquals("editorial in harbour", styles.dressed(null).toString());
        assertEquals("neumorphism", styles.slug(styles.dressed("neumorphism_clay")), "a style in its own palette is named alone");
        assertEquals(56, styles.themes().size(), "seven styles, each in eight palettes: every house palette is of the base, as every house style (D22)");
        for (DesignStyle<?> s : HouseStyles.STYLES)
            assertTrue(styles.palettes().contains(styles.own(s)), s.token() + " has a palette of its own among the house's");
    }
}
