package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import java.util.List;

/** neumorphism's look of toggle-widget: base's, by delegation. */
public record ToggleWidget() implements CastingStylePerRole<BaseScheme, Neumorphism, HousePreferences.ToggleWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HousePreferences.ToggleWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ToggleWidget());
    }

    @Override public PartsSpec<BaseScheme, Neumorphism, HousePreferences.ToggleWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePreferences.ToggleWidget.INSTANCE, HouseControls.Switch.INSTANCE, HouseDoing.Toggle.INSTANCE, new SwitchToggle())));
    }
}
