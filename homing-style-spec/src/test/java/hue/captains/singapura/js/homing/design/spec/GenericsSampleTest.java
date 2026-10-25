package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The types, as an assignment and its implementations are written (RFC 0067, D54): what compiles holds the rules the
 * compiler is given - an assignment names implementations of its own component and its own layer, an implementation of
 * another component, or of another layer, not fitting it.
 */
class GenericsSampleTest {

    record BasePlain() implements CssImplementation<BaseScheme, Base, HouseControls.PlainButton> {
        @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() { return List.of(new BaseColours.Border()); }
    }

    record BasePlainGo() implements CssImplementation<BaseScheme, Base, HouseControls.PlainButton> {}

    record EditorialPlain() implements CssImplementation<BaseScheme, Editorial, HouseControls.PlainButton> {}

    record EditorialActionBar() implements CssImplementation<BaseScheme, Editorial, HouseContainers.ActionBar> {}

    @Test
    void anAssignment_namesItsComponentsOwnImplementations_byValue() {
        var base = new ComponentAssignment<>(HouseControls.PlainButton.INSTANCE, new BasePlain(), List.of(
                new RoleAssignment<>(HouseDoing.Action.INSTANCE, new BasePlain()),
                new RoleAssignment<>(HouseDoing.Go.INSTANCE, new BasePlainGo())));
        assertEquals(List.of(new Casting(HouseControls.PlainButton.INSTANCE), new Casting(HouseControls.PlainButton.INSTANCE, HouseDoing.Action.INSTANCE),
                new Casting(HouseControls.PlainButton.INSTANCE, HouseDoing.Go.INSTANCE)), base.castings());
        assertEquals(Optional.of(new BasePlain()), base.of(new Casting(HouseControls.PlainButton.INSTANCE, HouseDoing.Action.INSTANCE)),
                "a role drawn as its component is names the same implementation: no delegation");
        assertEquals(Optional.of(new BasePlainGo()), base.of(new Casting(HouseControls.PlainButton.INSTANCE, HouseDoing.Go.INSTANCE)));
        assertEquals(Optional.empty(), base.of(new Casting(HouseControls.PlainButton.INSTANCE, HouseDoing.Cancel.INSTANCE)));
        assertEquals(List.of(new BasePlain(), new BasePlainGo()), base.implementations());
        var editorial = new ComponentAssignment<>(HouseControls.PlainButton.INSTANCE, new EditorialPlain(), List.of(
                new RoleAssignment<>(HouseDoing.Action.INSTANCE, new EditorialPlain()),
                new RoleAssignment<>(HouseDoing.Go.INSTANCE, new EditorialPlain())));
        assertEquals(List.of(new EditorialPlain()), editorial.implementations(), "two castings meant alike: one target");
    }

    @Test
    void aCasting_isAssignedOnce() {
        assertThrows(IllegalArgumentException.class, () -> new ComponentAssignment<>(HouseControls.PlainButton.INSTANCE, new BasePlain(), List.of(
                new RoleAssignment<>(HouseDoing.Go.INSTANCE, new BasePlain()),
                new RoleAssignment<>(HouseDoing.Go.INSTANCE, new BasePlainGo()))));
    }

    @Test
    void aRoleThatOnlyPlacesItsPart_isAssignedNoLook() {
        assertThrows(IllegalArgumentException.class, () -> new ComponentAssignment<>(HouseContainers.ActionBar.INSTANCE, new EditorialActionBar(), List.of(
                new RoleAssignment<>(HouseShaping.Actions.INSTANCE, new EditorialActionBar()))));
    }
}
