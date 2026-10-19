package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMonitors;
import hue.captains.singapura.js.homing.ui.taxonomy.UiMonitorsComponentModule;

import java.util.List;

/**
 * UiMonitorsComponentModule's components in Base, in the typed spec stack (RFC 0067, D53, D54): each component it brings - its own, and
 * the parts that come in with them - and the implementation each of its castings wears, on its own and in each role it
 * is cast in.
 */
public record UiMonitorsBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiMonitorsBaseSpec INSTANCE = new UiMonitorsBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiMonitorsComponentModule.INSTANCE; }

    @Override public List<ComponentAssignment<BaseScheme, Base, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HouseMonitors.FocusMonitor.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.FocusMonitor(), List.of()),
                new ComponentAssignment<>(HouseMonitors.StewardMonitor.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.StewardMonitor(), List.of()));
    }
}
