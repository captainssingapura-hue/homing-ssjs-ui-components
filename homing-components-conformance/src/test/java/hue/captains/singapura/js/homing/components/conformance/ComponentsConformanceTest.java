package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.conformance.engine.ConformanceEngine;
import hue.captains.singapura.js.homing.conformance.engine.ServedModuleRenderer;
import hue.captains.singapura.js.homing.conformance.rules.CrateConformance;
import hue.captains.singapura.js.homing.conformance.rules.CrateCoverage;
import hue.captains.singapura.js.homing.conformance.rules.CssConformance;
import hue.captains.singapura.js.homing.conformance.rules.DeclaredDebts;
import hue.captains.singapura.js.homing.conformance.rules.Finding;
import hue.captains.singapura.js.homing.conformance.rules.GradedFinding;
import hue.captains.singapura.js.homing.component.ComponentDetails;
import hue.captains.singapura.js.homing.component.ComponentTrees;
import hue.captains.singapura.js.homing.component.Implementations;
import hue.captains.singapura.js.homing.component.keyboard.KeyboardRegistry;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.tree.TreeLevel;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.menu.ContextMenuRegistry;
import hue.captains.singapura.js.homing.design.styling.InstalledStyling;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The components' gate, strict from the first day: the baseline is empty and
 * every finding fails the build. Its checks over the fresh crates —
 * <ol>
 *   <li><b>crate integrity</b>: each declares every served module in its
 *       Maven module and every cross-crate import in {@code requires()};</li>
 *   <li><b>coverage</b>: nothing served from those modules is uncrated;</li>
 *   <li><b>the lanes</b>: the served artifact of every module, graded by the
 *       framework's policy under the role its crate declares;</li>
 *   <li><b>the CSS graph laws</b>: tokens declared, priors are palettes,
 *       nested references declared, crate reach, no literal families;</li>
 *   <li><b>the catalogue</b>: every component a crate exports is listed by
 *       the crate's own catalogue, once, and the composed tree derived from
 *       the closure has no two vertices alike.</li>
 * </ol>
 * The export and the served studio grade by the same configuration.
 */
class ComponentsConformanceTest {

    /** The house's own instance, installed as a site installs its own: what is made from its taxonomy is served (D51). */
    @BeforeAll
    static void theHousesInstance_installed() { InstalledStyling.install(HouseStyles.INSTANCE.styling()); }

    private static final boolean ALLOW_PRE_EXISTING =
            Boolean.parseBoolean(System.getProperty("conformance.allowPreExisting", "true"));

    @Test
    void everyCrateIsStructurallyComplete() {
        CrateConformance.Result result = CrateConformance.evaluate(new ArrayList<>(ComponentsConformance.closure()));
        for (Crate c : ComponentsConformance.TOP_LEVEL) {
            CrateConformance.CrateResult crate = result.crates().get(c.name());
            assertNotNull(crate, c.name() + " must be present in the evaluation");
            assertEquals(List.of(), crate.orphans(), c.name() + ": every served module must be declared");
            assertEquals(List.of(), crate.illegalImports(), c.name() + ": every cross-crate import must be declared in requires()");
        }
    }

    @Test
    void everyServedModuleIsCrated() {
        List<String> gaps = CrateCoverage.check(ComponentsConformance.closure(), ComponentsConformance.ANCHORS);
        assertEquals(List.of(), gaps, () -> "served modules missing from every crate:\n" + String.join("\n", gaps));
    }

    @Test
    void everyServedModuleKeepsItsLane() {
        List<Finding> raw = new ConformanceEngine(ComponentsConformance.POLICY, new ServedModuleRenderer())
                .checkCrates(ComponentsConformance.TOP_LEVEL);
        assertEquals(List.of(), DeclaredDebts.INSTANCE.unbalanced(ComponentsConformance.TOP_LEVEL, raw), "a debt not as declared - owed exactly, never more");
        List<GradedFinding> graded = ComponentsConformance.grader(ALLOW_PRE_EXISTING).grade(raw);
        List<GradedFinding> errors = graded.stream().filter(GradedFinding::isError).toList();
        assertEquals(List.of(), errors, () -> "conformance ERRORS (" + errors.size() + "):\n"
                + errors.stream().map(ComponentsConformanceTest::describe).collect(Collectors.joining("\n")));
        assertEquals(0, ComponentsConformance.baseline().size(), "the baseline is empty on purpose; a finding is fixed, not filed");
    }

    @Test
    void theCssGraphKeepsItsLaws() {
        Set<String> own = ComponentsConformance.ownModules();
        List<Finding> raw = CssConformance.check(ComponentsConformance.closure())
                .stream().filter(f -> own.contains(f.moduleClass())).toList();
        List<GradedFinding> errors = ComponentsConformance.grader(ALLOW_PRE_EXISTING).grade(raw)
                .stream().filter(GradedFinding::isError).toList();
        assertEquals(List.of(), errors, () -> "css graph ERRORS (" + errors.size() + "):\n"
                + errors.stream().map(ComponentsConformanceTest::describe).collect(Collectors.joining("\n")));
    }

    private static String describe(GradedFinding g) {
        Finding f = g.finding();
        return f.moduleClass() + " [" + f.rule().value() + "] " + f.message()
                + (g.note().isBlank() ? "" : "  (" + g.note() + ")");
    }

    /**
     * The logical side against the physical: a component is a declared export
     * of a DOM module, listed by its crate's catalogue; the site's tree is
     * derived from the closure, every vehicle grafted under one root.
     */
    @Test
    void everyComponentIsCatalogued_andTheCompositionIsSound() {
        assertEquals(List.of(), ComponentTrees.validate(ComponentsConformance.TOP_LEVEL));
        var composed = ComponentTrees.compose("components", ComponentsConformance.TOP_LEVEL);
        var vehicles = composed.root().children().stream().map(n -> n.segment().value()).toList();
        assertEquals(List.of("server", "ui-elements", "ui-icons", "ui-dialog", "ui-floating", "ui-preferences", "ui-split-grid", "ui-pan-zoom", "ui-panes", "ui-monitors", "ui-menu", "mpa", "ui-control-panel"), vehicles, "one vehicle per crate that ships components, in closure order — the base first, which sizing requires (sizing ships no component); the elements require it too (the keyboard steward) and the icons (a knob's mark), the dialog the floating crate, the preferences the split grid their view is laid out by; docking catalogues nothing - the desk, its hand, its floater and its docking are statics; the control panel, a widget, last");
        var root = (ComponentDetails.OfComposition) composed.detailsOf(composed.root().identity());
        assertEquals(13, root.vehicleCount());
        assertEquals(96, root.componentCount(), "the components declared so far - the slider group, the panel and its bar dropped (20 Oct): 1 base (the keyboard steward), 64 elements (the browser's own text box, checkbox, range and select, the switch and its groove and knob, the slider's rail, groove, fill and knob, a grip, nine buttons, nineteen texts - code, a table cell's words (D42) and a badge of each tone among them - a heading for each of six levels among them - a link, a crumb, a scrim, a separator, a delimiter, the brand's mark, a pane's thumbnail, the plain and the clickable card, the summary's view, a menu's item, a list's row and a choice's option, a lamp, a drop mark, a detent, a tick and a boundary line, the slider and the edge strip, the ground, the site bar, the brand and the trail), 1 icon, 1 dialog, 8 preferences (a setting's row among them), 2 pan-zoom (the view and its bar), 7 panes (the tab, a component of its own now, the tab-pane, the single-tab pane a float carries, the opener and the picker among them; the new-tab control and its picture retired for now), 2 floating, 2 split grid, 2 monitors, 2 menus, 3 chrome and the control panel");
        assertTrue(composed.root().children().stream().allMatch(v -> v.level() == TreeLevel.L1.INSTANCE), "every vehicle grafted one under the root");
        // what the components need of a page is derived from the catalogue: nothing invisible, nothing nameless
        assertEquals(List.of(), ContextMenuRegistry.validate(ComponentsConformance.TOP_LEVEL));
        assertEquals(List.of("split", "tab"), ContextMenuRegistry.requiredBy(ComponentsConformance.TOP_LEVEL).kinds().stream().map(k -> k.kind()).toList(), "the tab pane opens both - a chip's menu, and its strip ground's, which the desk's docking answers - by name; a site serving it holds them");
    }

    /**
     * The keys, through the party: a component that declares its keys has no
     * key listener of its own, and the served modules that still listen for
     * themselves are the ones not yet migrated, held here so the list only
     * shrinks. The page's keyboard map is derived from the closure.
     */
    @Test
    void keysComeThroughTheParty_andTheMigrationListOnlyShrinks() {
        assertEquals(List.of(), KeyboardRegistry.validate(ComponentsConformance.TOP_LEVEL));
        var map = KeyboardRegistry.requiredBy(ComponentsConformance.TOP_LEVEL);
        assertEquals(List.of("ClickableCard", "ContextMenuSteward", "Dialog", "FloatLayer", "ListMasterWidget", "MultiTabPane", "PreferencesView", "Slider",
                        "SplitGridMirror", "SvgPanZoom", "TabOpener"),
                map.byComponent().keySet().stream().map(c -> c.getClass().getSimpleName()).sorted().toList(), "the components that take keys: leaves, and the holders that hand keys on to what is inside them");
        assertEquals(6, map.takersOf("ArrowUp").size(), "the slider (its group dropped, 20 Oct), the mirror, the list master and the view over it, the menu, the pan-zoom view");
        assertEquals(List.of(), KeyboardRegistry.undeclaredListeners(ComponentsConformance.TOP_LEVEL), "the ledger is empty: every key comes through the party");
    }

    /**
     * Every implementation anchored at the house's taxonomy: a component's
     * module implements its leaf, one leaf one module one class, a shared base
     * only under its branch; and what a leaf's module imports of other
     * components is what its parts declare, each a component of its own - a part
     * nothing implements is never realized by its owner inline (owner, 20 Oct).
     * The leaves not yet composed as declared are held in the ledger, so the
     * list only shrinks: each is a question for the design - the declaration or
     * the code is wrong.
     */
    @Test
    void everyImplementationIsAnchored_andComposedAsItsLeafDeclares() {
        List<String> problems = Implementations.INSTANCE.validate(HouseTaxonomy.INSTANCE.read(HouseTaxonomy.MODULES), ComponentsConformance.TOP_LEVEL);
        assertEquals(COMPOSITION_LEDGER, problems, () -> "implementations against the taxonomy (" + problems.size() + "):\n" + String.join("\n", problems));
    }

    /**
     * Drawn without the component the leaf declares: the float's bar its own strip. And every part its owner made
     * inline until the inline reading was withdrawn (owner, 20 Oct) - each to become a component of its own: the
     * lip, the title bar and the action bar, whose behaviour their owners hear, once a
     * component's own events are settled (RFC 0071 port, question 2). And the preferences button's old module, which still
     * opens the dialog its taxonomy no longer gives it (owner, 20 Oct): ported, it hands its click to its owner, the site's
     * chrome - a widget - which opens it. The rooms - a strip's tail, the one left - are no
     * parts at all: their owners' own (Component.rooms()).
     */
    private static final List<String> COMPOSITION_LEDGER = List.of(
            "EdgeStripModule (EdgeStrip): its part reveal is Lip, which no module implements - a part is a component of its own, never realized by its owner inline",
            "DialogModule (Dialog): its part actions is ActionBar, which no module implements - a part is a component of its own, never realized by its owner inline",
            "SingleTabPaneModule (SingleTabPane) never imports TabStrip, which its part tabs calls for",
            "FloatingPaneModule (FloatingPane): its part head is TitleBar, which no module implements - a part is a component of its own, never realized by its owner inline");   // the preferences button's gone with it, no component (21 Oct)
}
