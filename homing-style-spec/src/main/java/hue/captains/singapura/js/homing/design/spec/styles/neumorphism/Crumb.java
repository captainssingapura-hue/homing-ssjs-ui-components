package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** neumorphism's implementation of crumb: CSS of its own. */
public record Crumb() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.Crumb, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.Crumb, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }
}
