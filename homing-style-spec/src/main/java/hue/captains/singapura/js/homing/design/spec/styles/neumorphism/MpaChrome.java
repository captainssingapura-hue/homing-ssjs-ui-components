package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;

/** neumorphism's implementation of mpa-chrome: the base's, by delegation. */
public record MpaChrome() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseSite.MpaChrome, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseSite.MpaChrome, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.MpaChrome());
    }
}
