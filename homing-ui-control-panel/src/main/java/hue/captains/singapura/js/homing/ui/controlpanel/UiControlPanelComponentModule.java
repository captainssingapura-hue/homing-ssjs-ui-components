package hue.captains.singapura.js.homing.ui.controlpanel;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;

import java.util.List;

/**
 * This jar's components, for the components taxonomy an instance registers at bootstrap: those it implements - a flat list; their parts come in with them.
 */
public record UiControlPanelComponentModule() implements ComponentModule {

    public static final UiControlPanelComponentModule INSTANCE = new UiControlPanelComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseControlPanel.ControlPanel.INSTANCE);
    }
}
