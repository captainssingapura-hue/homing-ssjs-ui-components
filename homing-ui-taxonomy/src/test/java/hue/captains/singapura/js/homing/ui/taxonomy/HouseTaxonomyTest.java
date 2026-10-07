package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
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
 * it; and every component the house ships today has its leaf. Its parts are yet to be declared over
 * the catalogue.
 */
class HouseTaxonomyTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read();

    /** The components ui-components ships today, by their class names; its Button is realized by the button leaves. */
    private static final Set<String> SHIPPED = Set.of(
            "Card", "Slider", "SliderGroup", "Panel", "EdgeStrip", "Dialog", "DockGrid", "FloatLayer", "FloatingPane",
            "FocusMonitor", "StewardMonitor", "Icon", "ContextMenu", "ContextMenuSteward", "AddTab", "MultiTabPane",
            "PaneThumbs", "SingleTabPane", "TabOpener", "TabPane", "TabPicker", "TabStrip", "PanZoomBar", "SvgPanZoom",
            "SplitPane", "SplitGridMirror", "SplitGrid", "MpaChrome", "PreferencesButton", "ThemeWidget", "ChoiceWidget",
            "ListMasterWidget", "OverviewWidget", "PreferenceField", "PreferencesView", "ScaleWidget", "ToggleWidget");

    @Test
    void theHouseIsReadWhole_withItsCatalogue() {
        assertEquals(16, HOUSE.branches().size());
        assertEquals(81, HOUSE.components().size());
        assertEquals(67, HOUSE.roles().size(), "the catalogue beside the tree");
        assertTrue(HOUSE.parts().isEmpty(), "the parts are yet to be declared as slots over the catalogue");
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
        assertEquals(List.of(1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2),
                HOUSE.branches().stream().map(ComponentBranch::level).toList(), "seven at level 1, nine at level 2");
    }

    @Test
    void everyComponentShippedToday_hasItsLeaf() {
        var names = HOUSE.components().stream().map(c -> c.getClass().getSimpleName()).collect(Collectors.toSet());
        assertTrue(names.containsAll(SHIPPED), "missing: " + SHIPPED.stream().filter(n -> !names.contains(n)).toList());
        assertTrue(HOUSE.children(HouseBranches.Button.INSTANCE).size() >= 6, "the button's looks are buttons of their own");
    }

    private static List<Component<?>> descendants(ComponentBranch b) {
        return HOUSE.components().stream().filter(c -> HOUSE.fallback(c).contains(b)).toList();
    }
}
