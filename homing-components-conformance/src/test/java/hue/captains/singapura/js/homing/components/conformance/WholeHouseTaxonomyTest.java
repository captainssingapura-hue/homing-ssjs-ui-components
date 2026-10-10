package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Part;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtensions;
import hue.captains.singapura.js.homing.ui.controllability.HouseControllability;
import hue.captains.singapura.js.homing.ui.panes.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.tao.ontology.enforcer.OntologyEnforcer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The whole house: the base tree and every module that extends it, read where every module is.
 * Each module that ships components declares its own leaves beside them, and they are in the tree
 * because the module is on the classpath; together they are the house's taxonomy as it was before
 * it was cut, refused nothing. Every node stateless; every role of the catalogue named but one;
 * every node means something; every leaf a control type, its module's own where it has one.
 */
class WholeHouseTaxonomyTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read();

    @Test
    void everyModuleThatShipsComponents_extendsTheHouse_withItsOwnLeaves() {
        var extensions = TaxonomyExtensions.INSTANCE.of(HouseTaxonomy.class);
        assertEquals(List.of("HousePreferences", "HouseSite", "HouseControlPanel", "HouseDocking", "HouseFloating", "HouseMenus", "HouseMonitors",
                             "HousePanes", "HousePanZoom", "HouseSplitGrid"),
                extensions.stream().map(e -> e.getClass().getSimpleName()).toList(), "in the order of their class names");
        var leaves = new LinkedHashMap<String, Integer>();
        for (TaxonomyExtension e : extensions) {
            for (Component<?> c : e.components())
                assertEquals(e.getClass(), c.getClass().getEnclosingClass(), c + " is declared by the module that lists it");
            leaves.put(e.getClass().getSimpleName(), e.components().size());
        }
        assertEquals(30, leaves.values().stream().mapToInt(Integer::intValue).sum(), "30 leaves the modules add: " + leaves);
        assertEquals(57, HouseTaxonomy.INSTANCE.common().size(), "to the 57 common ones - a pane's thumbnail among them, a thumbnail's and a grid's mirror's; a tab, its strip and where a dragged one lands");
    }

    @Test
    void theWholeHouse_isReadAsItWasBeforeItWasCut() {
        assertEquals(17, HOUSE.branches().size());
        assertEquals(87, HOUSE.components().size());
        assertEquals(91, HOUSE.roles().size(), "the catalogue beside the tree");
        assertEquals(110, HOUSE.parts().size(), "the slots, each a part once its owner is appended");
        assertEquals(46, HOUSE.parts().stream().map(Part::owner).distinct().count(), "the components that have parts");
    }

    /** Every node of the whole house a stateless functional object, as jOntology judges one: the extensions' too. */
    @Test
    void everyNode_isStateless() {
        var nodes = new ArrayList<Object>();
        nodes.addAll(HOUSE.branches());
        nodes.addAll(HOUSE.components());
        nodes.addAll(HOUSE.roleBranches());
        nodes.addAll(HOUSE.roles());
        nodes.addAll(HOUSE.states().branches());
        nodes.addAll(HOUSE.states().types());
        nodes.addAll(HOUSE.events().branches());
        nodes.addAll(HOUSE.events().types());
        var enforcer = new OntologyEnforcer();
        var violations = nodes.stream().map(Object::getClass).distinct()
                .flatMap(c -> enforcer.enforce(c).stream().map(v -> c.getName() + ": " + v.message()))
                .toList();
        assertEquals(List.of(), violations);
    }

    /**
     * Every word of the catalogue the whole house says, but those it has for the common components it
     * has no leaf for yet - kept whole so a component is built from them - Host, a floater's, and Cell, a
     * grid's own cell's, which the mirror drew until its cells became pane thumbnails.
     */
    @Test
    void everyRoleNamed_butTheWordsKeptForWhatIsToCome() {
        assertEquals(List.of("placeholder", "error", "check", "query", "skip", "increase", "decrease", "clear", "remove",
                             "previous", "next", "first", "last", "more", "foot", "host", "body", "media", "fallback", "editor",
                             "cell", "group", "column", "opener", "pull", "scroll"),
                HOUSE.roles().stream().filter(r -> HOUSE.partsNaming(r).isEmpty()).map(r -> r.name().value()).toList());
    }

    @Test
    void everyNode_meansSomething_theExtensionsBesideTheirCode() {
        var nodes = new ArrayList<String>();
        HOUSE.branches().forEach(b -> nodes.add(HOUSE.meaning(b).markdown()));
        HOUSE.components().forEach(c -> nodes.add(HOUSE.meaning(c).markdown()));
        HOUSE.roleBranches().forEach(b -> nodes.add(HOUSE.meaning(b).markdown()));
        HOUSE.roles().forEach(r -> nodes.add(HOUSE.meaning(r).markdown()));
        HOUSE.states().branches().forEach(b -> nodes.add(HOUSE.meaning(b).markdown()));
        HOUSE.states().types().forEach(s -> nodes.add(HOUSE.meaning(s).markdown()));
        HOUSE.events().branches().forEach(b -> nodes.add(HOUSE.meaning(b).markdown()));
        HOUSE.events().types().forEach(e -> nodes.add(HOUSE.meaning(e).markdown()));
        assertEquals(17 + 87 + 21 + 91 + 8 + 22 + 7 + 14, nodes.size(), "no module adds a state or an event: the house's trees are whole");
        assertTrue(nodes.stream().noneMatch(String::isBlank));
    }

    @Test
    void anExtensionsLeaf_composedOfTheBasesAndItsOwn() {
        assertEquals(List.of("multi-tab-pane-tabs 1 tab-strip", "multi-tab-pane-picker 0..1 tab-picker", "multi-tab-pane-note 0..1 caption"),
                said(HousePanes.MultiTabPane.INSTANCE), "the strip a common leaf, the picker its module's own");
        var chip = HOUSE.partsOf(HousePanes.TabPane.INSTANCE).get(0);
        assertEquals(List.of(ExtentAxis.SIZE, ExtentAxis.ASPECT), HOUSE.extents(chip), "a tab-pane's chip is a tab, and has a tab's");
    }

    // ── controllability ────────────────────────────────────────────────────

    @Test
    void theExtensionsLeaves_classifiedBesideThem() {
        var expected = new LinkedHashMap<String, String>();
        for (String o : List.of("context-menu", "float-layer", "tab-opener")) expected.put(o, "overlay");
        expected.put("svg-pan-zoom", "zoomable");
        for (String a : List.of("split-pane", "split-grid", "split-grid-mirror", "pane-thumbs", "dock-grid")) expected.put(a, "arrangeable");
        for (String m : List.of("focus-monitor", "steward-monitor", "context-menu-steward")) expected.put(m, "monitor");
        for (String p : List.of("list-master-widget", "add-tab", "pan-zoom-bar", "mpa-chrome")) expected.put(p, "plain");
        var actual = new LinkedHashMap<String, String>();
        for (String token : expected.keySet()) actual.put(token, typeOf(token));
        assertEquals(expected, actual);
        assertEquals(9 + 8, HouseControllability.INSTANCE.classification().declared().size(), "the common nine, and eight the modules declare - the split grid's mirror, a control, arranged as the grid is");
        for (Component<?> leaf : HOUSE.components())
            assertTrue(HouseControllability.INSTANCE.findControlType(HOUSE, leaf) != null, leaf + " has a type");
    }

    private static String typeOf(String token) {
        Component<?> leaf = HOUSE.components().stream().filter(c -> c.token().equals(token)).findFirst().orElseThrow();
        return HouseControllability.INSTANCE.findControlType(HOUSE, leaf).name();
    }

    /** A component's parts, each said as its token, how many, and what plays it. */
    private static List<String> said(Component<?> owner) {
        return HOUSE.partsOf(owner).stream().map(p -> p.token() + " " + p.cardinality().multiplicity() + " " + p.base().token()).toList();
    }
}
