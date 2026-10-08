package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Showing;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Viewing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's controllability, read and constructed - so the catalogue holds together - files its
 * twenty-three options in eight categories; its degrees are the core's axes; every option is
 * applied by a method of its own; every type takes options of the catalogue and never a degree;
 * and every leaf of the house's taxonomy has a type, found up its lineage.
 */
class HouseControllabilityTest {

    private static final ControlCatalogue CATALOGUE = HouseControllability.INSTANCE.catalogue();
    private static final Taxonomy TAXONOMY = HouseTaxonomy.INSTANCE.read();

    @Test
    void eightCategories_twentyThreeOptions_eachInItsCategory() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("degrees", List.of("colour", "size", "aspect"));
        expected.put("states", List.of("enabled", "current", "raised", "held"));
        expected.put("showing", List.of("open-modal", "open", "close"));
        expected.put("viewing", List.of("zoom-in", "zoom-out", "fit"));
        expected.put("arranging", List.of("split", "even-out", "reset-layout"));
        expected.put("placing", List.of("move", "resize", "ring"));
        expected.put("content", List.of("next", "clear", "missing"));
        expected.put("asking", List.of("ask"));
        var actual = new LinkedHashMap<String, List<String>>();
        for (ControlNode b : CATALOGUE.children(ControlRoot.INSTANCE))
            actual.put(b.name().value(), CATALOGUE.children((ControlBranch) b).stream().map(n -> n.name().value()).toList());
        assertEquals(expected, actual);
        assertEquals(23, CATALOGUE.options().size());
    }

    @Test
    void theDegrees_areTheCoresAxes_restingWhereTheyRest() {
        var degrees = CATALOGUE.options().stream().filter(o -> o instanceof WithExtent<?>).map(o -> (WithExtent<?>) o).toList();
        assertEquals(List.of(ExtentAxis.values()), degrees.stream().map(WithExtent::axis).toList());
        for (WithExtent<?> d : degrees) {
            assertEquals(d.axis().name().toLowerCase(), d.name().value(), "a degree is named for its axis");
            assertEquals(d.axis().rest(), d.rest());
        }
    }

    @Test
    void everyOption_saysHowItIsControlled_andTheMethodThatAppliesIt() {
        var means = new LinkedHashMap<String, String>();
        var methods = new LinkedHashMap<String, String>();
        for (ControlOption<?> o : CATALOGUE.options()) { means.put(o.name().value(), o.means()); methods.put(o.name().value(), o.method()); }
        assertEquals(Map.of("extent", 3L, "switch", 4L, "action", 15L, "question", 1L),
                means.values().stream().collect(java.util.stream.Collectors.groupingBy(m -> m, java.util.stream.Collectors.counting())));
        // where the house's components already answer by a method, the option names it
        assertEquals("extent", methods.get("colour"));
        assertEquals("setOn", methods.get("enabled"));
        assertEquals("highlight", methods.get("current"));
        assertEquals("hold", methods.get("held"));
        // otherwise its name, in camel case
        assertEquals("zoomIn", methods.get("zoom-in"));
        assertEquals("resetLayout", methods.get("reset-layout"));
        assertEquals("openModal", methods.get("open-modal"));
    }

    @Test
    void everyType_takesOptionsOfTheCatalogue() {
        for (ControlType t : HouseControllability.INSTANCE.classification().types())
            for (ControlOption<?> o : t.options())
                assertTrue(CATALOGUE.options().contains(o), t.name() + " takes " + o + ", which the catalogue does not file");
    }

    @Test
    void theDemoedLeaves_areClassifiedByType() {
        var expected = new LinkedHashMap<String, String>();
        for (String b : List.of("plain-button", "primary-button", "secondary-button", "danger-button", "warning-button", "success-button", "slider"))
            expected.put(b, "switchable");
        expected.put("panel", "emphasised");
        expected.put("edge-strip", "holdable");
        expected.put("dialog", "modal-overlay");
        for (String o : List.of("context-menu", "float-layer", "tab-opener")) expected.put(o, "overlay");
        expected.put("svg-pan-zoom", "zoomable");
        for (String a : List.of("split-pane", "split-grid", "split-grid-mirror", "pane-thumbs")) expected.put(a, "arrangeable");
        expected.put("floating-pane", "placeable");
        expected.put("icon", "content");
        for (String m : List.of("focus-monitor", "steward-monitor", "context-menu-steward")) expected.put(m, "monitor");
        for (String p : List.of("summary-card", "slider-group", "list-master-widget", "add-tab", "pan-zoom-bar")) expected.put(p, "plain");
        var actual = new LinkedHashMap<String, String>();
        for (String token : expected.keySet()) actual.put(token, typeOf(token));
        assertEquals(expected, actual);
    }

    @Test
    void aBranchClassifiesEveryLeafUnderIt_andANodeWithNothingOnItsLineageIsPlain() {
        assertEquals("switchable", typeOf("toggle-button"), "under the Button branch, declared on the branch");
        assertEquals("arrangeable", typeOf("dock-grid"), "under the Split branch, declared on the branch");
        assertEquals("plain", typeOf("heading"));
        for (Component<?> leaf : TAXONOMY.components())
            assertTrue(HouseControllability.INSTANCE.findControlType(TAXONOMY, leaf) != null, leaf + " has a type");
    }

    @Test
    void aLeafIsControlledBy_itsAxesDegrees_thenItsTypesOptions() {
        assertEquals(List.of("colour", "size", "enabled"), optionsOf("danger-button"));
        assertEquals(List.of("size", "aspect"), optionsOf("summary-card"));
        assertEquals(List.of("open-modal", "open", "close"), optionsOf("dialog").subList(optionsOf("dialog").size() - 3, optionsOf("dialog").size()));
    }

    private static List<String> optionsOf(String token) {
        Component<?> leaf = TAXONOMY.components().stream().filter(c -> c.token().equals(token)).findFirst().orElseThrow();
        return HouseControllability.INSTANCE.optionsFor(TAXONOMY, leaf).stream().map(o -> o.name().value()).toList();
    }

    // ── what is refused ───────────────────────────────────────────────────

    /** An option controlled by two means at once. */
    record Both() implements WithAction<Showing>, WithSwitch<Showing> {
        @Override public Showing parent() { return Showing.INSTANCE; }
        @Override public boolean rest() { return false; }
    }

    /** A second option named as the house's size is. */
    record Size() implements WithAction<Viewing> {
        @Override public Viewing parent() { return Viewing.INSTANCE; }
    }

    @Test
    void anOptionOfTwoMeans_aNameTwice_andAMethodTwice_areRefused() {
        var twoMeans = assertThrows(RefusedControls.class, () -> ReadControls.INSTANCE.read(List.of(new Both())));
        assertTrue(twoMeans.problems().get(0).contains("controlled by 2 means"), twoMeans.getMessage());
        var options = new java.util.ArrayList<ControlOption<?>>(HouseControllability.INSTANCE.options());
        options.add(new Size());
        var twice = assertThrows(RefusedControls.class, () -> ReadControls.INSTANCE.read(options));
        assertTrue(twice.problems().stream().anyMatch(p -> p.startsWith("'size' names 2 nodes")), twice.getMessage());
        assertTrue(twice.problems().stream().anyMatch(p -> p.startsWith("the method 'size' applies 2 options")), twice.getMessage());
    }

    @Test
    void aTypeNamingADegree_andANodeClassifiedTwice_areRefused() {
        var degree = assertThrows(RefusedControls.class, () -> new ControlType("sized", List.of(HouseControlOptions.Size.INSTANCE)));
        assertTrue(degree.getMessage().contains("the axes are the component's own"), degree.getMessage());
        var at = new ControlTypeAt(hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers.Panel.INSTANCE, HouseControllability.PLAIN);
        var twice = assertThrows(RefusedControls.class, () -> new ControlClassification(List.of(at, at), HouseControllability.PLAIN));
        assertTrue(twice.getMessage().contains("declared twice"), twice.getMessage());
    }

    private static String typeOf(String token) {
        Component<?> leaf = TAXONOMY.components().stream().filter(c -> c.token().equals(token)).findFirst()
                .orElseThrow(() -> new AssertionError("no leaf '" + token + "' in the house's taxonomy"));
        return HouseControllability.INSTANCE.findControlType(TAXONOMY, leaf).name();
    }
}
