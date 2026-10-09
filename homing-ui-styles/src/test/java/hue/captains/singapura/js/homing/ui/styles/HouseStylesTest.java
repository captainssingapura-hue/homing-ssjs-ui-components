package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Dressed;
import hue.captains.singapura.js.homing.design.semantic.FlattenSheet;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.design.semantic.SemanticTokens;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house styles: every one written over Base, which answers every root that is not colour and
 * what they all say alike; each style answering the button branch's minimal set and nothing more -
 * a silence costs nothing, so a style that has nothing to say of a class leaves it to Base - and
 * every theme flattening whole over the house's taxonomy.
 */
class HouseStylesTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read();

    /** What a style must decide for the button branch: the button's frame and motion, the control's ring and how it is when it cannot be used. */
    private static final Set<String> BUTTON_BRANCH = Set.of(
            "button shape-corner", "button shape-border", "button size-inset", "button size-gap", "button size-extent",
            "button type-face", "button motion-ease", "button shape-shadow", "button motion-transform",
            "control shape-outline", "control effect-opacity", "control affordance-cursor",
            "toggle-button shape-shadow", "toggle-button motion-transform", "close-button type-glyph",
            "close-button shape-border", "close-button size-inset", "close-button size-extent", "close-button shape-shadow", "close-button motion-transform",
            "icon-button shape-border", "icon-button size-inset", "icon-button size-extent", "icon-button shape-shadow", "icon-button motion-transform");

    @Test
    void theStyles_areSeven_eachOverBase_andBaseOverNothing() {
        assertEquals(List.of("editorial", "neo-brutalism", "neo-futurism", "neumorphism", "glassmorphism", "retro-futurism", "sketchy"),
                HouseStyles.STYLES.stream().map(DesignStyle::token).toList(), "the old slugs, Editorial first");
        for (DesignStyle s : HouseStyles.STYLES)
            assertTrue(s instanceof Derives<?> d && d.base() == Base.INSTANCE, s.token() + " is written over Base");
        assertEquals(List.of("base"), new Dressed(Base.INSTANCE, Harbour.INSTANCE).chain(), "Base is written over nothing");
        assertEquals(List.of("editorial", "base"), HouseStyles.THEMES.get("editorial").chain());
    }

    @Test
    void eachStyle_answersTheButtonBranchsMinimalSet_andLeavesOnlyItsSilencesToBase() {
        Map<String, Set<String>> left = new java.util.LinkedHashMap<>();
        for (DesignStyle s : HouseStyles.STYLES) {
            Set<String> answered = classes(s.implementations());
            assertTrue(BUTTON_BRANCH.containsAll(answered), () -> s.token() + " answers beyond the button branch: " + answered);
            var missing = new TreeSet<>(BUTTON_BRANCH);
            missing.removeAll(answered);
            left.put(s.token(), missing);
        }
        assertEquals(Map.of("editorial", Set.of(), "neo-brutalism", Set.of(), "neo-futurism", Set.of(),
                        "neumorphism", Set.of("button shape-border", "toggle-button motion-transform"), "glassmorphism", Set.of(), "retro-futurism", Set.of(),
                        "sketchy", Set.of("button shape-shadow", "toggle-button shape-shadow")), left,
                "neumorphism draws no border and its press does not move; sketchy casts no shadow: each leaves it to Base, whose root says none");
    }

    @Test
    void base_answersEveryRootThatIsNotColour_andWhatEveryStyleSaysAlike() {
        var roots = Base.INSTANCE.implementations().stream().filter(i -> i.node().component() instanceof Root).map(i -> i.target().token()).toList();
        var semantic = SemanticTokens.INSTANCE.leaves();
        assertEquals(semantic.stream().filter(t -> !(t instanceof Target.Color)).map(Target::token).collect(Collectors.toSet()), Set.copyOf(roots),
                "the twenty-seven roots that are not colour, the box's line and its ring apart, and what is drawn over its ground");
        assertEquals(27, roots.size());
        var alike = new TreeSet<>(classes(Base.INSTANCE.implementations()));
        alike.removeIf(c -> c.startsWith("root "));
        var marks = new TreeSet<String>();
        for (String mark : List.of("close-button", "icon-button"))
            for (String t : List.of("shape-border", "size-inset", "size-extent", "shape-shadow", "motion-transform", "type-scale")) marks.add(mark + " " + t);
        var expected = new TreeSet<>(Set.of("control affordance-cursor", "button type-scale", "control shape-shadow", "control motion-transform"));
        expected.addAll(marks);
        assertEquals(expected, alike,
                "a control is pointed at, a button's type grows alike, a control that cannot be used neither lifts nor casts a shadow,"
                + " and a mark alone goes without a frame, in the size of the text it sits in");
        var palette = Harbour.INSTANCE.implementations().stream().filter(i -> i.node().component() instanceof Root).map(i -> i.target().token()).toList();
        assertEquals(semantic.stream().filter(t -> t instanceof Target.Color).map(Target::token).collect(Collectors.toSet()), Set.copyOf(palette),
                "the palette answers every semantic colour at the root");
        assertEquals(List.of("color-ink", "color-fill", "color-stroke", "color-scrollbar", "color-background", "color-border", "color-outline",
                        "color-column-rule", "color-box-shadow"),
                semantic.stream().filter(t -> t instanceof Target.Color).map(Target::token).toList(), "nine semantic colours");
    }

    /** No style names a colour: a shadow is drawn in the box shadow's colours, which the palette gives. */
    @Test
    void theStylesNameNoColour_theirShadowsReadThePalettes() {
        String editorial = FlattenSheet.INSTANCE.flatten(HOUSE, HouseStyles.THEMES.get("editorial"), "editorial").css();
        assertTrue(editorial.contains("/* from harbour: root__color-box-shadow */\n.primary-button__color-box-shadow {\n"
                + "    --box-shadow-cast: #111936; --box-shadow-lit: #FFFFFF; --box-shadow-glow: #1E2761;\n}\n"), "the palette's, on the element itself");
        assertTrue(editorial.contains("box-shadow: 0 6px 16px color-mix(in srgb, var(--box-shadow-cast) 12%, transparent);"), "the style's, reading it");
        assertTrue(editorial.contains("/* from harbour: control__color-outline--focus */\n.primary-button__color-outline--focus {\n"), "the ring's colour on focus");
        assertTrue(editorial.contains("/* from harbour: primary-button__color-border */\n.primary-button_go__color-border {\n    border-color: #1E2761;\n}\n"),
                "the box's line, its own");
    }

    @Test
    void everyTheme_flattensWhole_andAnswersOnlyWhatTheTaxonomyAsks() {
        for (Dressed theme : HouseStyles.THEMES.values()) {
            assertEquals(List.of(), theme.check(HOUSE), theme.toString());
            var flat = FlattenSheet.INSTANCE.flatten(HOUSE, theme, theme.toString());
            assertEquals(List.of(), flat.unanswered(), () -> theme + ": every target answered at the root");
            long marked = HOUSE.castings().stream().filter(c -> c.component() == hue.captains.singapura.js.homing.ui.taxonomy.HouseControls.CloseButton.INSTANCE).count();
            assertEquals(HOUSE.castings().size() * 32 + marked, flat.looks(),
                    () -> theme + ": every casting on every CSS target the semantic classes answer; the glyph a silence but the close button's mark");
        }
        String sketchy = FlattenSheet.INSTANCE.flatten(HOUSE, HouseStyles.THEMES.get("sketchy"), "sketchy").css();
        assertTrue(sketchy.contains("/* from base: root__shape-shadow */\n.danger-button__shape-shadow {\n    box-shadow: none;\n}\n"), "ink casts no shadow");
        assertTrue(sketchy.contains("/* from sketchy: control__shape-outline--focus */\n.danger-button__shape-outline--focus {\n"), "its own ring");
    }

    @Test
    void aPageIsWornInTheStyleAndPaletteItsSlugNames_theDefaultsOtherwise() {
        var styles = HouseStyles.INSTANCE;
        assertEquals("sketchy in harbour", styles.dressed("sketchy").toString());
        assertEquals("sketchy in forest", styles.dressed("sketchy_forest").toString());
        assertEquals("sketchy in harbour", styles.dressed("sketchy_sunset").toString(), "a palette the house has not yet: the style's own");
        assertEquals("editorial in harbour", styles.dressed("flat-morphism").toString(), "retired: the default");
        assertEquals("editorial in forest", styles.dressed("flat-morphism_forest").toString(), "the default style, in the palette named");
        assertEquals("editorial in harbour", styles.dressed(null).toString());
        assertEquals("neumorphism_forest", styles.slug(styles.dressed("neumorphism_forest")));
        assertEquals(14, HouseStyles.THEMES.size(), "seven styles, each in two palettes");
    }

    /**
     * The palettes are written by one rule, so they answer the same semantic colours in the same
     * places and differ only in what the colours are; and a style casts its shadow in whatever
     * palette it is worn in - its own sheet unchanged, only the colours the shadow reads.
     */
    @Test
    void theHousePalettes_answerAlike_andAStyleCastsInWhicheverItIsWornIn() {
        assertEquals(classes(Harbour.INSTANCE.implementations()), classes(Forest.INSTANCE.implementations()), "one rule, two sets of seeds");
        String harbour = FlattenSheet.INSTANCE.flatten(HOUSE, HouseStyles.THEMES.get("editorial"), "editorial").css();
        String forest = FlattenSheet.INSTANCE.flatten(HOUSE, HouseStyles.THEMES.get("editorial_forest"), "editorial_forest").css();
        String shadow = "/* from editorial: button__shape-shadow--hover */\n.primary-button__shape-shadow--hover {\n";
        assertTrue(harbour.contains(shadow) && forest.contains(shadow), "the style's shadow, the same in both");
        assertTrue(harbour.contains("--box-shadow-cast: #111936; --box-shadow-lit: #FFFFFF; --box-shadow-glow: #1E2761;"), "cast in the harbour's navy");
        assertTrue(forest.contains("/* from forest: root__color-box-shadow */\n.primary-button__color-box-shadow {\n"
                + "    --box-shadow-cast: #1A3829; --box-shadow-lit: #FFFFFF; --box-shadow-glow: #2D5F3F;\n}\n"), "cast in the forest's pine");
        assertTrue(forest.contains("/* from forest: control__color-outline--focus */"), "the ring, the forest's honey");
        assertTrue(forest.contains("outline-color: color-mix(in srgb, #D4A04C 55%, #D4DFCC);"));
    }

    private static Set<String> classes(List<? extends Implementation<?>> implementations) {
        var tokens = SemanticTokens.INSTANCE;
        return implementations.stream().map(i -> tokens.casting(i.node()) + " " + i.target().token()).collect(Collectors.toSet());
    }
}
