package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Kind;
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
 * The house's taxonomy is read whole and refused nothing; everything reached is declared; every
 * kind has a component under it; and every component the house ships today has its leaf.
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
    void theHouseIsReadWhole() {
        assertEquals(16, HOUSE.kinds().size());
        assertEquals(81, HOUSE.components().size());
        assertEquals(HOUSE.components().stream().mapToInt(c -> c.roles().size()).sum(), HOUSE.parts().size(), "every role a part");
    }

    @Test
    void everythingReached_isDeclared() {
        assertEquals(Set.copyOf(HouseTaxonomy.INSTANCE.components()), Set.copyOf(HOUSE.components()),
                "no component plays a role without being declared in the house");
    }

    @Test
    void everyKind_hasAComponentUnderIt() {
        for (Kind<?> k : HOUSE.kinds())
            assertFalse(descendants(k).isEmpty(), k.getClass().getSimpleName() + " has no component under it");
        assertEquals(List.of("control", "item", "container", "region", "text", "mark", "track"),
                HOUSE.children(Root.INSTANCE).stream().map(n -> n.token()).toList());
    }

    @Test
    void everyComponentShippedToday_hasItsLeaf() {
        var names = HOUSE.components().stream().map(c -> c.getClass().getSimpleName()).collect(Collectors.toSet());
        assertTrue(names.containsAll(SHIPPED), "missing: " + SHIPPED.stream().filter(n -> !names.contains(n)).toList());
        assertTrue(HOUSE.children(HouseKinds.Button.INSTANCE).size() >= 6, "the button's looks are buttons of their own");
    }

    @Test
    void aPartIsAnsweredAsWhatItIs() {
        var title = HOUSE.partsOf(HouseContainers.Card.INSTANCE).get(1);
        assertEquals("card-title", title.token());
        assertEquals(List.of("card-title", "heading", "text", "root"), HOUSE.fallback(title).stream().map(n -> n.token()).toList());
    }

    private static List<Component<?>> descendants(Kind<?> k) {
        return HOUSE.components().stream().filter(c -> chainOf(c).contains(k)).toList();
    }

    private static List<?> chainOf(Component<?> c) { return HOUSE.fallback(c); }
}
