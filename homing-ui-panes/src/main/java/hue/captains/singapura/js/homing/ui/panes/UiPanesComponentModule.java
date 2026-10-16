package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

import java.util.List;

/**
 * This jar's components, for the components taxonomy an instance registers at bootstrap: those it implements, then those
 * its owners realize inline and it gives its look to - a flat list; their parts come in with them.
 */
public record UiPanesComponentModule() implements ComponentModule {

    public static final UiPanesComponentModule INSTANCE = new UiPanesComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HousePanes.MultiTabPane.INSTANCE, HousePanes.SingleTabPane.INSTANCE,
                       HouseContainers.TabStrip.INSTANCE, HouseControls.Tab.INSTANCE, HousePanes.TabPane.INSTANCE,
                       HousePanes.TabOpener.INSTANCE, HousePanes.TabPicker.INSTANCE, HousePanes.AddTab.INSTANCE);
    }
}
