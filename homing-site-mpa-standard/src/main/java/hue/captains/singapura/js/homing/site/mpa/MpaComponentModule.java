package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;

import java.util.List;

/**
 * This jar's components, for the components taxonomy an instance registers at bootstrap: those it implements - a flat list; their parts come in with them.
 */
public record MpaComponentModule() implements ComponentModule {

    public static final MpaComponentModule INSTANCE = new MpaComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseSite.MpaChrome.INSTANCE, HouseControls.PreferencesButton.INSTANCE,
                       HouseSite.ThemeWidget.INSTANCE);
    }
}
