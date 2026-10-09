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
            "button shape-corner", "button shape-rule", "button size-inset", "button size-gap", "button size-extent",
            "button type-face", "button motion-ease", "button shape-shadow", "button motion-transform",
            "control shape-rule", "control effect-opacity", "control affordance-cursor");

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
                        "neumorphism", Set.of("button shape-rule"), "glassmorphism", Set.of(), "retro-futurism", Set.of(),
                        "sketchy", Set.of("button shape-shadow")), left,
                "neumorphism draws no rule and sketchy casts no shadow: each leaves it to Base, whose root says none");
    }

    @Test
    void base_answersEveryRootThatIsNotColour_andWhatEveryStyleSaysAlike() {
        var roots = Base.INSTANCE.implementations().stream().filter(i -> i.node().component() instanceof Root).map(i -> i.target().token()).toList();
        assertEquals(Target.leaves().stream().filter(t -> !(t instanceof Target.Color)).map(Target::token).collect(Collectors.toSet()), Set.copyOf(roots),
                "the twenty-five roots that are not colour");
        assertEquals(25, roots.size());
        var alike = new TreeSet<>(classes(Base.INSTANCE.implementations()));
        alike.removeIf(c -> c.startsWith("root "));
        assertEquals(new TreeSet<>(Set.of("control affordance-cursor", "button type-scale", "control shape-shadow", "control motion-transform")), alike,
                "a control is pointed at, a button's type grows alike, and a control that cannot be used neither lifts nor casts a shadow");
        var palette = Harbour.INSTANCE.implementations().stream().filter(i -> i.node().component() instanceof Root).map(i -> i.target().token()).toList();
        assertEquals(6, palette.size(), "the palette answers the six colour roots");
    }

    @Test
    void everyTheme_flattensWhole_andAnswersOnlyWhatTheTaxonomyAsks() {
        for (Dressed theme : HouseStyles.THEMES.values()) {
            assertEquals(List.of(), theme.check(HOUSE), theme.toString());
            var flat = FlattenSheet.INSTANCE.flatten(HOUSE, theme, theme.toString());
            assertEquals(List.of(), flat.unanswered(), () -> theme + ": every target answered at the root");
            assertEquals(HOUSE.castings().size() * 27, flat.looks(), () -> theme + ": every casting on every CSS target but the glyph, a silence");
        }
        String sketchy = FlattenSheet.INSTANCE.flatten(HOUSE, HouseStyles.THEMES.get("sketchy"), "sketchy").css();
        assertTrue(sketchy.contains("/* from base: root__shape-shadow */\n.danger-button__shape-shadow {\n    box-shadow: none;\n}\n"), "ink casts no shadow");
        assertTrue(sketchy.contains("/* from sketchy: control__shape-rule--focus */\n.danger-button__shape-rule--focus {\n"), sketchy.substring(0, 400));
    }

    @Test
    void aPageIsWornInTheStyleItsSlugNames_theDefaultOtherwise() {
        var styles = HouseStyles.INSTANCE;
        assertEquals("sketchy in harbour", styles.dressed("sketchy").toString());
        assertEquals("sketchy in harbour", styles.dressed("sketchy_forest").toString(), "the palette the old slug names is not the house's yet");
        assertEquals("editorial in harbour", styles.dressed("flat-morphism").toString(), "retired: the default");
        assertEquals("editorial in harbour", styles.dressed(null).toString());
    }

    private static Set<String> classes(List<? extends Implementation<?>> implementations) {
        var tokens = SemanticTokens.INSTANCE;
        return implementations.stream().map(i -> tokens.casting(i.node()) + " " + i.target().token()).collect(Collectors.toSet());
    }
}
