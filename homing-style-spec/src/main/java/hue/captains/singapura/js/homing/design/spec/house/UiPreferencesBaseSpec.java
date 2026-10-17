package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPreferencesComponentModule;

import java.util.List;

/** UiPreferencesComponentModule's standalones in Base, in the typed spec stack (RFC 0067, D53): its components', and those of their design a functional role places. */
public record UiPreferencesBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiPreferencesBaseSpec INSTANCE = new UiPreferencesBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiPreferencesComponentModule.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Base, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HousePreferences.PreferencesView.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesViewDeclaration())),
                new StandaloneEntry<>(HousePreferences.PreferenceField.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PreferenceFieldDeclaration())),
                new StandaloneEntry<>(HousePreferences.ChoiceWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ChoiceWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ToggleWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ToggleWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ScaleWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ScaleWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.SettingRow.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SettingRowDeclaration())),
                new StandaloneEntry<>(HousePreferences.OverviewWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.OverviewWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ListMasterWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ListMasterWidgetDeclaration())),
                new StandaloneEntry<>(HouseRegions.WidgetSlot.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.WidgetSlot())));
    }
}
