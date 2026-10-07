package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Every leaf of the house's taxonomy is placed once: a specimen that builds it live, shown by the
 * page around the specimens, realized with its specimen to come, or realized by nothing yet - and
 * the page's registry is generated from that one declaration.
 */
class HouseSpecimensTest {

    private static final HouseSpecimens HOUSE = HouseSpecimens.INSTANCE;

    @Test
    void everyLeafOfTheHouse_placedOnce() {
        var placed = new ArrayList<Component<?>>();
        HOUSE.specimens().forEach(s -> placed.add(s.leaf()));
        HOUSE.aroundIt().forEach(a -> placed.add(a.leaf()));
        placed.addAll(HOUSE.pending());
        placed.addAll(HOUSE.unrealized());
        assertEquals(placed.size(), new HashSet<>(placed).size(), "a leaf placed twice");
        assertEquals(Set.copyOf(HouseTaxonomy.INSTANCE.read().components()), Set.copyOf(placed),
                "every leaf of the house, and nothing else");
    }

    @Test
    void everyRealizedLeaf_shownInAction_byASpecimenOrByThePageAroundIt() {
        assertEquals(28, HOUSE.specimens().size(), "twelve elements; eight overlays, menus and splits; eight the page around does not show");
        assertEquals(16, HOUSE.aroundIt().size(), "the workspace's six, the chrome's two, the preferences' eight");
        assertEquals(List.of(), HOUSE.pending(), "none still to come");
        assertEquals(41, HOUSE.unrealized().size());
        assertEquals(List.of("ButtonSpecimen"), HOUSE.specimens().stream().filter(s -> s.leaf() == HouseControls.DangerButton.INSTANCE)
                .map(Specimen::className).toList(), "the six buttons are one button");
    }

    @Test
    void theRegistry_generatedFromTheDeclaration() {
        String js = String.join("\n", HouseSpecimensModule.INSTANCE.selfContent(null));
        assertTrue(js.contains("\"danger-button\": ButtonSpecimen"), js);
        assertTrue(js.contains("\"dialog\": DialogSpecimen"), js);
        assertTrue(js.contains("\"add-tab\": AddTabSpecimen"), js);
        assertTrue(js.contains("\"dock-grid\": \"the workspace itself"), js);
        assertTrue(js.contains("const HOUSE_PENDING = Object.freeze([]);"), js);
        assertTrue(js.contains("\"heading\""), "an unrealized leaf: " + js);
        assertEquals(23, HouseSpecimensModule.INSTANCE.imports().getAllImports().size(), "each specimen's module imported once, whatever it shows");
    }
}
