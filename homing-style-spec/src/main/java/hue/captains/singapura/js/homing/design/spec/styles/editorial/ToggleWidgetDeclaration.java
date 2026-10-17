package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** editorial's declaration of toggle-widget: its own implementation, and a spec for each of its slots. */
public record ToggleWidgetDeclaration() implements CastingDeclaration<BaseScheme, Editorial, HousePreferences.ToggleWidget, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Editorial, HousePreferences.ToggleWidget, RoleRoot> own() { return new ToggleWidget(); }

    @Override public PartsSpec<BaseScheme, Editorial, HousePreferences.ToggleWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HousePreferences.ToggleWidget.INSTANCE, HousePreferences.PreferenceField.INSTANCE, HouseShaping.Setting.INSTANCE),
                new PartSpec.SameRole<>(HousePreferences.ToggleWidget.INSTANCE, HouseControls.Switch.INSTANCE, HouseDoing.Toggle.INSTANCE, new Target.Declared<>(new SwitchToggleDeclaration()))));
    }
}
