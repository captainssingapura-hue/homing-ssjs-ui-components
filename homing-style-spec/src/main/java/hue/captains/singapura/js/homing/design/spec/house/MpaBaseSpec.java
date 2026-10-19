package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.RoleAssignment;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;
import hue.captains.singapura.js.homing.ui.taxonomy.MpaComponentModule;

import java.util.List;

/**
 * MpaComponentModule's components in Base, in the typed spec stack (RFC 0067, D53, D54): each component it brings - its own, and
 * the parts that come in with them - and the implementation each of its castings wears, on its own and in each role it
 * is cast in.
 */
public record MpaBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final MpaBaseSpec INSTANCE = new MpaBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return MpaComponentModule.INSTANCE; }

    @Override public List<ComponentAssignment<BaseScheme, Base, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HouseSite.MpaChrome.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.MpaChrome(), List.of()),
                new ComponentAssignment<>(HouseControls.PreferencesButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButton()))),
                new ComponentAssignment<>(HouseSite.ThemeWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ThemeWidget(), List.of()));
    }
}
