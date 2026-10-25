package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.CaptionModule;
import hue.captains.singapura.js.homing.ui.elements.MarkBehaviorModule;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

import java.util.List;

/** The setting's row: one setting of a group, listed with its value - a simple component on its own {@code <div role="listitem">}, its value a caption; still. */
public record SettingRowModule() implements DomModule<SettingRowModule>, ImplFor<HousePreferences.SettingRow> {

    /** {@code new SettingRow(branch, new FixedSettingRow(), place?)}; {@code root}, {@code label}, {@code value}. */
    public record SettingRow() implements BranchComponent<SettingRowModule> {
        @Override public String summary() { return "One setting of a group, listed with its value."; }
    }

    public static final SettingRowModule INSTANCE = new SettingRowModule();

    @Override
    public ImportsFor<SettingRowModule> imports() {
        return ImportsFor.<SettingRowModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferencesStyles.pv_setting_row(), new PreferencesStyles.pv_setting_row_value()), PreferencesStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SettingRowModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SettingRow()));
    }
}
