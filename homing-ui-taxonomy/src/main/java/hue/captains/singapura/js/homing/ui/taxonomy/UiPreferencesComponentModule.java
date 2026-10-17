package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;

import java.util.List;

/**
 * homing-preferences's component module, defined beside the components it lists (D40, D46): those the jar implements - a flat list; their parts come in with them.
 * A definition alone - what a style jar's check needs of the jar - while the jar keeps the implementation.
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
