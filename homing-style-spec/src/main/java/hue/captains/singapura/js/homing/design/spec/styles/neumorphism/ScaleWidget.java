package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** neumorphism's implementation of scale-widget: the base's, by delegation. */
public record ScaleWidget() implements CastingStylePerRole<BaseScheme, Neumorphism, HousePreferences.ScaleWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HousePreferences.ScaleWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ScaleWidget());
    }
}
