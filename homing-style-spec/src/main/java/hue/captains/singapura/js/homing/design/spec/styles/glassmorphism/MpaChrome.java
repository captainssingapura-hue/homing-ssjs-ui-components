package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;

/** glassmorphism's implementation of mpa-chrome: the base's, by delegation. */
public record MpaChrome() implements ParentDelegation<BaseScheme, Base, Glassmorphism, HouseSite.MpaChrome, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseSite.MpaChrome, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.MpaChrome(); }
}
