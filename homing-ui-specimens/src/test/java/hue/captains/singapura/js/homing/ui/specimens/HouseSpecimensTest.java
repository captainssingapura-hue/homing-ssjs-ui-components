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
 * Every leaf of the house's taxonomy is placed once: a specimen that builds it live, realized with
 * its specimen to come, or realized by nothing yet - and the page's registry is generated from
 * that one declaration.
 */
class HouseSpecimensTest {

    private static final HouseSpecimens HOUSE = HouseSpecimens.INSTANCE;

    @Test
    void everyLeafOfTheHouse_placedOnce() {
        var placed = new ArrayList<Component<?>>();
        HOUSE.specimens().forEach(s -> placed.add(s.leaf()));
        placed.addAll(HOUSE.pending());
        placed.addAll(HOUSE.unrealized());
        assertEquals(placed.size(), new HashSet<>(placed).size(), "a leaf placed twice");
        assertEquals(Set.copyOf(HouseTaxonomy.INSTANCE.read().components()), Set.copyOf(placed),
                "every leaf of the house, and nothing else");
    }

    @Test
    void theFirstRound_theElements() {
        assertEquals(12, HOUSE.specimens().size());
        assertEquals(32, HOUSE.pending().size());
        assertEquals(41, HOUSE.unrealized().size());
        assertEquals(List.of("ButtonSpecimen"), HOUSE.specimens().stream().filter(s -> s.leaf() == HouseControls.DangerButton.INSTANCE)
                .map(Specimen::className).toList(), "the six buttons are one button");
    }

    @Test
    void theRegistry_generatedFromTheDeclaration() {
        String js = String.join("\n", HouseSpecimensModule.INSTANCE.selfContent(null));
        assertTrue(js.contains("\"danger-button\": ButtonSpecimen"), js);
        assertTrue(js.contains("\"summary-card\": SummaryCardSpecimen"), js);
        assertTrue(js.contains("const HOUSE_PENDING = Object.freeze([\"dialog\""), js);
        assertTrue(js.contains("\"heading\""), "an unrealized leaf: " + js);
        assertEquals(7, HouseSpecimensModule.INSTANCE.imports().getAllImports().size(), "each specimen's module imported once, whatever it shows");
    }
}
