package hue.captains.singapura.js.homing.ui.monitors;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMonitors;

import java.util.List;

/**
 * This jar's components, for the components taxonomy an instance registers at bootstrap: those it implements - a flat list; their parts come in with them.
 */
public record UiMonitorsComponentModule() implements ComponentModule {

    public static final UiMonitorsComponentModule INSTANCE = new UiMonitorsComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseMonitors.FocusMonitor.INSTANCE, HouseMonitors.StewardMonitor.INSTANCE);
    }
}
