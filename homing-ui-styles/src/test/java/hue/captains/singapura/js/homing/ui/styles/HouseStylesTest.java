package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.FlattenLexicon;
import hue.captains.singapura.js.homing.design.semantic.StylingRes;
import hue.captains.singapura.js.homing.design.semantic.WriteWord;
import hue.captains.singapura.js.homing.design.styling.PaletteSheet;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.HouseColour;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseLexicon;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseWords;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's styles and palettes on files (RFC 0067, appendix Two Classes per Leaf): seven styles over Base, Base the
 * base layer answering content for every leaf, each style drawing the buttons its own way; the house's colour tree, every
 * palette whole over it, each tint worked out from the palette's seeds; and a page worn in the style and palette its
 * slug names.
 */
class HouseStylesTest {

    private static final Taxonomy TAXONOMY = HouseTaxonomy.INSTANCE.read();

    @Test
    void theStyles_areSeven_eachOverBase_andBaseOverNothing() {
        assertEquals(List.of("editorial", "neo-brutalism", "neo-futurism", "neumorphism", "glassmorphism", "retro-futurism", "sketchy"),
                HouseStyles.STYLES.stream().map(DesignStyle::token).toList(), "the old slugs, Editorial first");
        for (DesignStyle<?> s : HouseStyles.STYLES)
            assertTrue(s instanceof Derives<?, ?> d && d.base() == Base.INSTANCE, s.token() + " is written over Base");
        assertTrue(!Derives.class.isInstance(Base.INSTANCE), "Base is written over nothing: the base layer");
    }

    @Test
    void base_answersContentForEveryCasting_andEachStyleDrawsTheButtons_inEveryRole_absentElsewhere() {
        for (Casting c : TAXONOMY.castings())
            assertEquals(StylingRes.CssContent.INSTANCE, Base.INSTANCE.style(c), c.token() + ": the base layer is never absent (D26)");
        var go = TAXONOMY.castings().stream().filter(c -> c.token().equals("primary-button_go")).findFirst().orElseThrow();
        for (DesignStyle<?> s : HouseStyles.STYLES) {
            for (Component<?> b : HouseFamilies.BUTTONS)
                assertEquals(StylingRes.CssContent.INSTANCE, s.style(new Casting(b)), s.token() + " draws " + b.token());
            assertEquals(StylingRes.CssContent.INSTANCE, s.style(go), s.token() + " draws the primary button as Go too, a class of its own");
            assertEquals(StylingRes.Absence.INSTANCE, s.style(new Casting(HouseControls.Knob.INSTANCE)), s.token() + ": a knob is Base's, for now");
        }
        assertEquals(9, HouseFamilies.BUTTONS.size());
    }

    @Test
    void theHouseColourTree_reads_andEveryPaletteIsWholeOverIt() {
        var tree = HouseColours.TREE;
        for (ColorPalette<ColorScheme.Base> p : HouseStyles.PALETTES) {
            assertEquals(List.of(), tree.check(p), p.token());
            assertEquals(27, PaletteSheet.INSTANCE.write(tree, p).variables().size(), p.token() + ": every palettable colour of the house");
        }
        assertEquals(8, HouseStyles.PALETTES.size());
    }

    @Test
    void aTint_isWorkedOutFromThePalettesSeeds_inSrgb_alphaPremultiplied() {
        var rule = HousePalettes.INSTANCE;
        assertEquals(new ConcreteColor.Srgb("#f4b942"), rule.of(Harbour.SEEDS, new HouseColours.Accent()), "a seed as it is");
        assertEquals(new ConcreteColor.Srgb(17, 25, 54, 0.04).css(), rule.of(Harbour.SEEDS, new HouseColours.ShadeFaint()).css(),
                "the inverted layer at 4%");
        var ring = (ConcreteColor.Srgb) rule.of(Harbour.SEEDS, new HouseColours.Ring());
        assertEquals(List.of(236, 206, 144), List.of(ring.red(), ring.green(), ring.blue()), "the accent leaning 55% into the hairline");
        assertEquals("rgb(255 255 255 / 0.192)", rule.of(Frost.SEEDS, new HouseColours.Sheen()).css(), "glass stays glass: a third white, at 60%");
        assertTrue(Harbour.INSTANCE.color(new HouseColours.Text()).isPresent());
        assertTrue(Harbour.INSTANCE.color(new HouseColours.Text()).get() instanceof ConcreteColor.Srgb);
        HouseColour any = new HouseColours.Wash();
        assertEquals("rgb(59 74 107 / 0.12)", rule.of(Harbour.SEEDS, any).css(), "the text's ink, washed thin");
    }

    @Test
    void everyStyle_writesEveryWord_andItsCrossIsItsOwn() {
        var lexicon = HouseLexicon.INSTANCE.read();
        var write = WriteWord.INSTANCE;
        for (DesignStyle<?> s : HouseStyles.STYLES) {
            assertEquals(List.of(), write.problems(lexicon, s), s.token());
            var sheet = FlattenLexicon.INSTANCE.flatten(lexicon, s, s.token());
            assertEquals(List.of(), sheet.unwritten(), s.token());
            assertEquals(15, sheet.written(), s.token());
        }
        assertEquals(List.of(), write.problems(lexicon, Base.INSTANCE), "Base writes them all");
        var crosses = new java.util.LinkedHashMap<String, String>();
        for (DesignStyle<?> s : HouseStyles.STYLES) crosses.put(s.token(), write.write(s, HouseWords.Close.INSTANCE).orElseThrow().glyph());
        assertEquals(Map.of("editorial", "✕", "neo-brutalism", "╳", "neo-futurism", "⨯", "neumorphism", "✗", "glassmorphism", "×",
                "retro-futurism", "✘", "sketchy", "✖"), crosses, "each style's own cross, the close button's and every close row's alike");
    }

    @Test
    void aPageIsWornInTheStyleAndPaletteItsSlugNames_theStylesOwnOtherwise() {
        var styles = HouseStyles.INSTANCE;
        assertEquals("sketchy in marker", styles.dressed("sketchy").toString(), "a style alone is worn in its own palette");
        assertEquals("glassmorphism in frost", styles.dressed("glassmorphism").toString());
        assertEquals("sketchy in forest", styles.dressed("sketchy_forest").toString());
        assertEquals("glassmorphism in harbour", styles.dressed("glassmorphism_harbour").toString(), "any style in any palette");
        assertEquals("sketchy in marker", styles.dressed("sketchy_sunset").toString(), "a palette the house has not: the style's own");
        assertEquals("editorial in harbour", styles.dressed("flat-morphism").toString(), "retired: the default");
        assertEquals("editorial in harbour", styles.dressed(null).toString());
        assertEquals("neumorphism", styles.slug(styles.dressed("neumorphism_clay")), "a style in its own palette is named alone");
        assertEquals(56, HouseStyles.THEMES.size(), "seven styles, each in eight palettes");
        assertEquals("sketchy in forest", styles.switchTo(styles.dressed("editorial_forest"), Sketchy.INSTANCE).toString(),
                "a change of style keeps the palette worn: every house palette is of the base, as every house style (D22)");
        for (DesignStyle<?> s : HouseStyles.STYLES)
            assertTrue(HouseStyles.PALETTES.contains(styles.own(s)), s.token() + " has a palette of its own among the house's");
    }
}
