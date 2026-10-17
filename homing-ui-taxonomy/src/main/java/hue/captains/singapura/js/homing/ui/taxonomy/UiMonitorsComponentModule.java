package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;

import java.util.List;

/**
 * homing-ui-monitors's component module, defined beside the components it lists (D40, D46): those the jar implements - a flat list; their parts come in with them.
 * A definition alone - what a style jar's check needs of the jar - while the jar keeps the implementation.
 */
public record UiMonitorsComponentModule() implements ComponentModule {

    public static final UiMonitorsComponentModule INSTANCE = new UiMonitorsComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseMonitors.FocusMonitor.INSTANCE, HouseMonitors.StewardMonitor.INSTANCE);
    }
}
