package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentBranch;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Part;
import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyFinding;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's taxonomy is read whole, with its role catalogue, and refused nothing - jOntology
 * holding every node stateless; everything reached is declared; every branch has a component under
 * it; and every component the house ships today has its leaf. Its components declare their parts
 * as slots over the catalogue, and the catalogue's every role is named but one. Every node of both
 * trees means something, and the axes a branch declares are every leaf's under it.
 */
class HouseTaxonomyTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read();

    /** The components ui-components ships today, by their class names; its Button is realized by the button leaves, its Card by SummaryCard. */
    private static final Set<String> SHIPPED = Set.of(
            "SummaryCard", "Slider", "SliderGroup", "Panel", "EdgeStrip", "Dialog", "DockGrid", "FloatLayer", "FloatingPane",
            "FocusMonitor", "StewardMonitor", "Icon", "ContextMenu", "ContextMenuSteward", "AddTab", "MultiTabPane",
            "PaneThumbs", "SingleTabPane", "TabOpener", "TabPane", "TabPicker", "TabStrip", "PanZoomBar", "SvgPanZoom",
            "SplitPane", "SplitGridMirror", "SplitGrid", "MpaChrome", "PreferencesButton", "ThemeWidget", "ChoiceWidget",
            "ListMasterWidget", "OverviewWidget", "PreferenceField", "PreferencesView", "ScaleWidget", "ToggleWidget");

    @Test
    void theHouseIsReadWhole_withItsCatalogue() {
        assertEquals(17, HOUSE.branches().size());
        assertEquals(85, HOUSE.components().size());
        assertEquals(67, HOUSE.roles().size(), "the catalogue beside the tree");
        assertEquals(105, HOUSE.parts().size(), "the slots, each a part once its owner is appended");
        assertEquals(44, HOUSE.parts().stream().map(Part::owner).distinct().count(), "the components that have parts");
    }

    @Test
    void everythingReached_isDeclared() {
        assertEquals(Set.copyOf(HouseTaxonomy.INSTANCE.components()), Set.copyOf(HOUSE.components()),
                "no component is reached without being declared in the house");
    }

    @Test
    void theBranches_levelled_eachWithAComponentUnderIt() {
        for (ComponentBranch b : HOUSE.branches())
            assertFalse(descendants(b).isEmpty(), b.getClass().getSimpleName() + " has no component under it");
        assertEquals(List.of("control", "item", "container", "region", "text", "mark", "track"),
                HOUSE.children(Root.INSTANCE).stream().map(n -> n.token()).toList(), "level 1, in the order first reached");
        assertEquals(List.of(1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
                HOUSE.branches().stream().map(ComponentBranch::level).toList(), "seven at level 1, ten at level 2");
    }

    @Test
    void everyComponentShippedToday_hasItsLeaf() {
        var names = HOUSE.components().stream().map(c -> c.getClass().getSimpleName()).collect(Collectors.toSet());
        assertTrue(names.containsAll(SHIPPED), "missing: " + SHIPPED.stream().filter(n -> !names.contains(n)).toList());
        assertTrue(HOUSE.children(HouseBranches.Button.INSTANCE).size() >= 6, "the button's looks are buttons of their own");
    }

    @Test
    void theCard_aPlainLeaf_andACaseBuiltOnIt() {
        assertEquals(List.of(HouseContainers.PlainCard.INSTANCE, HouseContainers.SummaryCard.INSTANCE),
                HOUSE.children(HouseBranches.Card.INSTANCE));
        assertTrue(HOUSE.partsOf(HouseContainers.PlainCard.INSTANCE).isEmpty(), "the basic card fixes no part");
        assertEquals(List.of("summary-card-title 1 heading", "summary-card-tag 0..1 badge",
                             "summary-card-summary 0..1 caption", "summary-card-open 0..1 link"),
                said(HouseContainers.SummaryCard.INSTANCE));
    }

    @Test
    void aPart_playedByAnIndependentComponent_fallsBackThroughIt() {
        assertEquals(List.of("dialog-veil 0..1 scrim", "dialog-window 1 floating-pane", "dialog-actions 0..1 action-bar"),
                said(HouseContainers.Dialog.INSTANCE));
        assertEquals(List.of("tab-strip-add 0..1 icon-button", "tab-strip-count 1 pill", "tab-strip-close 0..1 close-button",
                             "tab-strip-landing 0..1 drop-mark"),
                said(HouseContainers.TabStrip.INSTANCE));
        var thumb = HOUSE.partsOf(HouseControls.Slider.INSTANCE).stream().filter(p -> p.role() == HouseDoing.Thumb.INSTANCE).findFirst().orElseThrow();
        assertEquals(List.of("slider-thumb", "knob", "handle", "control", "root"),
                HOUSE.fallback(thumb).stream().map(n -> n.token()).toList(), "through its base, never its owner");
    }

    @Test
    void everyRoleNamed_butHost() {
        assertEquals(List.of("ROLE_UNNAMED: Host is named by no component"),
                HOUSE.findings().stream().filter(f -> f.sign() == TaxonomyFinding.Sign.ROLE_UNNAMED).map(Object::toString).toList(),
                "a floater's host: the floater is not in the first cut");
    }

    @Test
    void everyNode_meansSomething_bothTrees() {
        var nodes = new java.util.ArrayList<String>();
        HOUSE.branches().forEach(b -> nodes.add(HOUSE.meaning(b).markdown()));
        HOUSE.components().forEach(c -> nodes.add(HOUSE.meaning(c).markdown()));
        HOUSE.roleBranches().forEach(b -> nodes.add(HOUSE.meaning(b).markdown()));
        HOUSE.roles().forEach(r -> nodes.add(HOUSE.meaning(r).markdown()));
        assertEquals(17 + 85 + 21 + 67, nodes.size());
        assertTrue(nodes.stream().noneMatch(String::isBlank));
        assertTrue(HOUSE.meaning(HouseControls.DangerButton.INSTANCE).markdown().startsWith("Does a thing that destroys"));
        assertTrue(HOUSE.meaning(HouseSaying.Title.INSTANCE).markdown().startsWith("Names a container"));
    }

    @Test
    void theAxes_aBranchsAreEveryLeafsUnderIt_aPartTakesItsOwnComponents() {
        assertEquals(List.of(ExtentAxis.SIZE), HOUSE.extents(HouseControls.PlainButton.INSTANCE), "every button has a size");
        assertEquals(List.of(ExtentAxis.SIZE, ExtentAxis.COLOUR), HOUSE.extents(HouseControls.DangerButton.INSTANCE),
                "a coloured button a colour too");
        assertEquals(List.of(ExtentAxis.SIZE, ExtentAxis.ASPECT), HOUSE.extents(HouseContainers.PlainCard.INSTANCE), "every card a size and an aspect");
        var chip = HOUSE.partsOf(HouseContainers.TabPane.INSTANCE).get(0);
        assertEquals(List.of(ExtentAxis.SIZE, ExtentAxis.ASPECT), HOUSE.extents(chip), "a tab-pane's chip is a tab, and has a tab's");
        var open = HOUSE.partsOf(HouseContainers.SummaryCard.INSTANCE).get(3);
        assertEquals(List.of(), HOUSE.extents(open), "a card's link has none of its card's: a part follows its own component");
    }

    /** A component's parts, each said as its token, how many, and what plays it. */
    private static List<String> said(Component<?> owner) {
        return HOUSE.partsOf(owner).stream().map(p -> p.token() + " " + p.cardinality().multiplicity() + " " + p.base().token()).toList();
    }

    private static List<Component<?>> descendants(ComponentBranch b) {
        return HOUSE.components().stream().filter(c -> HOUSE.fallback(c).contains(b)).toList();
    }
}
