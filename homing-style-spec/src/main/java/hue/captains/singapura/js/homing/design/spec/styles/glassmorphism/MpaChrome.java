package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;

/** glassmorphism's implementation of mpa-chrome: the base's, by delegation. */
public record MpaChrome() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseSite.MpaChrome, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseSite.MpaChrome, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.MpaChrome());
    }
}
