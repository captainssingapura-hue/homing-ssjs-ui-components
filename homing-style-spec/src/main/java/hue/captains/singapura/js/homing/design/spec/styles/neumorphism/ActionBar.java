package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neumorphism's implementation of action-bar: CSS of its own. */
public record ActionBar() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseContainers.ActionBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseContainers.ActionBar, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Border());
    }
}
