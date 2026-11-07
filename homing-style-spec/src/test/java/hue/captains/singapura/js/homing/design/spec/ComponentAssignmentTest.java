package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.WithRoles;
import hue.captains.singapura.js.homing.design.spec.styles.editorial.ActionBar;
import hue.captains.singapura.js.homing.design.spec.styles.editorial.Readout;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** A component said once wears its implementation in every casting; a component with no roles has the one casting. */
class ComponentAssignmentTest {

    @Test
    void oneImplementation_isWornInEveryRoleOfItsSet() {
        var worn = new Readout();
        var a = new ComponentAssignment<BaseScheme, Editorial, HouseText.Readout>(HouseText.Readout.INSTANCE, worn);
        var set = ((WithRoles<?>) HouseText.Readout.INSTANCE).roleSet().roles();

        assertFalse(set.isEmpty());
        assertEquals(1 + set.size(), a.castings().size());
        for (Casting c : a.castings()) assertSame(worn, a.of(c).orElseThrow());
        assertEquals(List.of(worn), a.implementations());
    }

    @Test
    void aComponentWithNoRoles_hasItsOneCasting() {
        var a = new ComponentAssignment<BaseScheme, Editorial, HouseContainers.ActionBar>(HouseContainers.ActionBar.INSTANCE, new ActionBar());
        assertEquals(1, a.castings().size());
        assertTrue(a.of(a.castings().getFirst()).isPresent());
    }
}
