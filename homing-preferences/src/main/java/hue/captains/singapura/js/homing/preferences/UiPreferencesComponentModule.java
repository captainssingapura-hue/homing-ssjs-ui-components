package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

import java.util.List;

/**
 * This jar's components, for the components taxonomy an instance registers at bootstrap: those it implements - a flat list; their parts come in with them.
 */
public record UiPreferencesComponentModule() implements ComponentModule {

    public static final UiPreferencesComponentModule INSTANCE = new UiPreferencesComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HousePreferences.PreferencesView.INSTANCE, HousePreferences.PreferenceField.INSTANCE,
                       HousePreferences.ChoiceWidget.INSTANCE, HousePreferences.ToggleWidget.INSTANCE,
                       HousePreferences.ScaleWidget.INSTANCE, HousePreferences.SettingRow.INSTANCE,
                       HousePreferences.OverviewWidget.INSTANCE, HousePreferences.ListMasterWidget.INSTANCE);
    }
}
