package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

import java.util.List;

/**
 * This jar's components, for the components taxonomy an instance registers at bootstrap: those it implements - a flat list; their parts come in with them.
 */
public record UiIconsComponentModule() implements ComponentModule {

    public static final UiIconsComponentModule INSTANCE = new UiIconsComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseMarks.Icon.INSTANCE);
    }
}
