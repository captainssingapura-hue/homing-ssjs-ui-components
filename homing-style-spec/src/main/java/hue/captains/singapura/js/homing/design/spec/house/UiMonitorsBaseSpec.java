package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMonitors;
import hue.captains.singapura.js.homing.ui.taxonomy.UiMonitorsComponentModule;
import java.util.List;

/** UiMonitorsComponentModule's components implemented in Base, in the typed spec stack (RFC 0067, D53). */
public record UiMonitorsBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiMonitorsBaseSpec INSTANCE = new UiMonitorsBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiMonitorsComponentModule.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Base, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseMonitors.FocusMonitor.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.FocusMonitor()),
                new Implemented<>(HouseMonitors.StewardMonitor.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.StewardMonitor()));
    }
}
