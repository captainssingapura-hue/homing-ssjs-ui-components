package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPreferencesComponentModule;

import java.util.List;

/**
 * UiPreferencesComponentModule's components in Base, in the typed spec stack (RFC 0067, D53, D54): each component it brings - its own, and
 * the parts that come in with them - and the implementation each of its castings wears, on its own and in each role it
 * is cast in.
 */
public record UiPreferencesBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiPreferencesBaseSpec INSTANCE = new UiPreferencesBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiPreferencesComponentModule.INSTANCE; }

    @Override public List<ComponentAssignment<BaseScheme, Base, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HousePreferences.PreferencesView.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesView(), List.of()),
                new ComponentAssignment<>(HousePreferences.PreferenceField.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferenceField(), List.of()),
                new ComponentAssignment<>(HousePreferences.ChoiceWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ChoiceWidget(), List.of()),
                new ComponentAssignment<>(HousePreferences.ToggleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ToggleWidget(), List.of()),
                new ComponentAssignment<>(HousePreferences.ScaleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ScaleWidget(), List.of()),
                new ComponentAssignment<>(HousePreferences.SettingRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SettingRow(), List.of()),
                new ComponentAssignment<>(HousePreferences.OverviewWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.OverviewWidget(), List.of()),
                new ComponentAssignment<>(HousePreferences.ListMasterWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ListMasterWidget(), List.of()),
                new ComponentAssignment<>(HouseRegions.WidgetSlot.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.WidgetSlot(), List.of()));
    }
}
