package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPreferencesComponentModule;
import java.util.List;

/** UiPreferencesComponentModule's components implemented in Base, in the typed spec stack (RFC 0067, D53). */
public record UiPreferencesBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiPreferencesBaseSpec INSTANCE = new UiPreferencesBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiPreferencesComponentModule.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Base, ?>> implementations() {
        return List.of(
                new Implemented<>(HousePreferences.PreferencesView.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesView()),
                new Implemented<>(HousePreferences.PreferenceField.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferenceField()),
                new Implemented<>(HousePreferences.ChoiceWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ChoiceWidget()),
                new Implemented<>(HousePreferences.ToggleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ToggleWidget()),
                new Implemented<>(HousePreferences.ScaleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ScaleWidget()),
                new Implemented<>(HousePreferences.SettingRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SettingRow()),
                new Implemented<>(HousePreferences.OverviewWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.OverviewWidget()),
                new Implemented<>(HousePreferences.ListMasterWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ListMasterWidget()));
    }
}
