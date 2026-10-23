package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Part;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.ui.controllability.HouseControllability;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.tao.ontology.enforcer.OntologyEnforcer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The whole house, read where every module is: the base tree and the modules' components, all defined in the
 * house's taxonomy (D40), each module's implementation in its own jar; together the house's taxonomy as it was
 * before it was cut, refused nothing. Every node stateless; every role of the catalogue named but one;
 * every node means something; every leaf a control type, its module's own where it has one.
 */
class WholeHouseTaxonomyTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read(HouseTaxonomy.MODULES);

    @Test
    void everyModulesComponents_areDefinedInTheHousesTaxonomy() {
        var holders = List.of("HousePreferences", "HousePanes", "HouseSplitGrid", "HousePanZoom", "HouseMenus");
        var leaves = new LinkedHashMap<String, Integer>();
        for (Component<?> c : HOUSE.components()) {
            String holder = c.getClass().getEnclosingClass().getSimpleName();
            if (holders.contains(holder)) leaves.merge(holder, 1, Integer::sum);
        }
        assertEquals(10, leaves.values().stream().mapToInt(Integer::intValue).sum(), "10 leaves the modules' components are - the tab picker and the tab opener widgets, transient, their caller's (22 Oct); the split grid a layout, its cell common (21 Oct) - the dock grid retired, its docks a split grid's residents; the float layer common now; the twelve widgets no components (20 Oct): " + leaves);
        assertEquals(119, HOUSE.components().size(), "the tab picker and the tab opener widgets, transient, their caller's (22 Oct); the pacman icon among them (21 Oct); with the 105 common ones - the plain container, the grid cell and the layouts' styling among them (21 Oct) - the twenty icons (D52), a table cell's words, the edge strip's lip, the rooms no components (20 Oct), the card plain or clickable and the summary's view, the slider's rail, knob and groove apart from the toggle's, the brand's mark and a pane's thumbnail among them, a thumbnail's and a grid's mirror's; a tab, its strip and where a dragged one lands; a site's brand and its preferences button; the page's ground and the site's bar; a floating pane's title bar, and the layer floating panes are stacked on; the browser's own text box and checkbox; a heading for each level of a page's outline; a document's code, quote and table, and a badge per tone");
    }

    @Test
    void theWholeHouse_isReadAsItWasBeforeItWasCut() {
        assertEquals(23, HOUSE.branches().size(), "the menu item a branch (23 Oct); the split gone with the split grid, a layout (21 Oct); " + "the icons a branch of the mark (D52); the headings a branch of the text; the input, the way and the selector under the control, the surface and the chooser under the container, each heading a family - the field and the cluster gone with the widgets (20 Oct)");
        assertEquals(119, HOUSE.components().size(), "the tab picker and the tab opener widgets, transient, their caller's (22 Oct); the icons each a leaf, the icon gone (D52); a table cell's words a text cell (D42); the edge strip's lip; the rooms no components, their owners' own (20 Oct); a document table's cells containers, their words strong or muted; the summary card ditched: the card plain or clickable, the summary a view of its own; the slider's rail, knob and groove and the toggle's parts of their own; the brand's mark a part of its own; the split pane retired - its uses widgets with a fixed split grid - and the dock grid - its docks a split grid's residents; the ground, the site bar and the title bar added (the panel's bar too, dropped with the panel, 20 Oct), and a text box and a checkbox; the heading a leaf per level; prose a leaf per element");
        assertEquals(99, HOUSE.roles().size(), "a choice's check, chosen (23 Oct); the catalogue beside the tree: what an icon is to its owner among them (D52); the umbrella and the sub-container, and the void role (21 Oct)");
        assertEquals(83, HOUSE.parts().size(), "the menu item two of a kind, the context menu three levels, each its rows, its lines and its submenus (23 Oct); a menu item's check (23 Oct); a multi-tab pane's floaters (23 Oct); the bar's own cross gone (22 Oct); the picker's and the opener's parts, and the multi-tab pane's picker, gone (22 Oct); the preferences button's face gone with it (21 Oct); the widgets' parts and the preferences button's dialog gone (20 Oct); the split grid's two lines the layouts' styling's, with its umbrellas and sub-containers (21 Oct); the close button's face, the close icon (D52); a header cell's title and a data cell's value, each a text cell (D42); the slots, each a part once its owner is appended - the strip's tabs, its tail a room now; the edge strip's lip; the slider's rail and the brand's mark among them; the grid's line in two roles; a table's header and data cells");
        assertEquals(36, HOUSE.parts().stream().map(Part::owner).distinct().count(), "the menu item two of a kind, the two menu levels (23 Oct); the picker and the opener no more (22 Oct); the preferences button no more (21 Oct); the close button among them, its face its own part (D52); the table's two cells among them; the components that have parts - the widgets no more (20 Oct); the slider's knob none: its mark the slider's symbol");
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
        var enforcer = new OntologyEnforcer();
        var violations = nodes.stream().map(Object::getClass).distinct()
                .flatMap(c -> enforcer.enforce(c).stream().map(v -> c.getName() + ": " + v.message()))
                .toList();
        assertEquals(List.of(), violations);
    }

    /**
     * Every word of the catalogue the whole house says, but those it has for the common components it
     * has no leaf for yet - kept whole so a component is built from them - Host, a floater's, Cell, a
     * grid's own cell's, which the mirror drew until its cells became pane thumbnails, and Dock, which the
     * dock grid gave its docks until they became a split grid's residents, the docking the desk's.
     */
    @Test
    void everyRoleNamed_butTheWordsKeptForWhatIsToCome() {
        assertEquals(List.of("subtitle", "category", "prompt", "placeholder", "status", "error", "check", "style", "palette", "picker", "query", "cancel", "skip",
                             "toggle", "adjust", "reset", "increase", "decrease", "clear", "add", "remove", "preferences", "previous", "next", "first",
                             "last", "more", "foot", "page", "host", "index", "detail", "setting", "layout", "body", "media", "fallback",
                             "member", "entry", "dock", "group", "opener", "pull", "scroll"),
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
        assertEquals(23 + 119 + 22 + 99 + 11 + 40, nodes.size(), "no module adds a state: the house's trees are whole");   // the tab carried and the detaches (23 Oct);   // the tab in use and the order of the tabs, a chip's close and the tabs' (22 Oct), a floating pane's (23 Oct)
        assertTrue(nodes.stream().noneMatch(String::isBlank));
    }

    @Test
    void anExtensionsLeaf_composedOfTheBasesAndItsOwn() {
        assertEquals(List.of("multi-tab-pane-tabs 1 tab-strip", "multi-tab-pane-note 0..1 caption", "multi-tab-pane-float 0..* floating-pane"),
                said(HousePanes.MultiTabPane.INSTANCE), "the strip a common leaf; no picker - what a new tab holds is its caller's (22 Oct); a floater for each tab torn off (23 Oct)");
        var chip = HOUSE.partsOf(HousePanes.TabPane.INSTANCE).get(0);
        assertEquals(List.of(ExtentAxis.SIZE, ExtentAxis.ASPECT), HOUSE.extents(chip), "a tab-pane's chip is a tab, and has a tab's");
    }

    // ── controllability ────────────────────────────────────────────────────

    @Test
    void theExtensionsLeaves_classifiedBesideThem() {
        var expected = new LinkedHashMap<String, String>();
        for (String o : List.of("context-menu", "float-layer")) expected.put(o, "overlay");   // the tab opener a widget (22 Oct)
        expected.put("svg-pan-zoom", "zoomable");
        for (String a : List.of("split-grid-mirror", "pane-thumbs")) expected.put(a, "arrangeable");   // the split grid a layout (21 Oct)
        expected.put("context-menu-steward", "monitor");
        for (String p : List.of("add-tab", "pan-zoom-bar")) expected.put(p, "plain");
        var actual = new LinkedHashMap<String, String>();
        for (String token : expected.keySet()) actual.put(token, typeOf(token));
        assertEquals(expected, actual);
        assertEquals(6 + 5, HouseControllability.INSTANCE.classification().declared().size(), "the common six - the panel, the one emphasised, dropped (20 Oct), the split a layout's (21 Oct) - and five the modules declare, the tab opener a widget (22 Oct), the monitors no components (20 Oct) - the split grid's mirror, a control, arranged as the grid is");
        for (Component<?> leaf : HOUSE.components())
            assertTrue(HouseControllability.INSTANCE.findControlType(HOUSE, leaf) != null, leaf + " has a type");
    }

    private static String typeOf(String token) {
        Component<?> leaf = HOUSE.components().stream().filter(c -> c.token().equals(token)).findFirst().orElseThrow();
        return HouseControllability.INSTANCE.findControlType(HOUSE, leaf).name();
    }

    /** A component's parts, each said as its token, how many, and what plays it - one, or each of several (D52). */
    private static List<String> said(Component<?> owner) {
        return HOUSE.partsOf(owner).stream().map(p -> p.token() + " " + p.cardinality().multiplicity() + " " + String.join("|", p.bases().stream().map(b -> b.token()).toList())).toList();
    }
}
