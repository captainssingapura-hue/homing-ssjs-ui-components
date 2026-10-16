package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMenus;

import java.util.List;

/**
 * This jar's components, for the components taxonomy an instance registers at bootstrap: those it implements - a flat list; their parts come in with them.
 */
public record UiMenuComponentModule() implements ComponentModule {

    public static final UiMenuComponentModule INSTANCE = new UiMenuComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseMenus.ContextMenuSteward.INSTANCE, HouseContainers.ContextMenu.INSTANCE);
    }
}
