package hue.captains.singapura.js.homing.ui.floating;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * This jar's components, for the components taxonomy an instance registers at bootstrap: those it implements - a flat list; their parts come in with them.
 */
public record UiFloatingComponentModule() implements ComponentModule {

    public static final UiFloatingComponentModule INSTANCE = new UiFloatingComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseContainers.FloatingPane.INSTANCE, HouseContainers.FloatLayer.INSTANCE);
    }
}
