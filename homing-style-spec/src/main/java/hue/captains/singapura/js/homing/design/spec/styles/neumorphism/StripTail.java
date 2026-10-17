package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** neumorphism's look of strip-tail: base's, by delegation. */
public record StripTail() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseRegions.StripTail, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseRegions.StripTail, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.StripTail());
    }
}
