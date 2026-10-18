package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;

/** sketchy's implementation of theme-widget: the base's, by delegation. */
public record ThemeWidget() implements ParentDelegation<BaseScheme, Base, Sketchy, HouseSite.ThemeWidget, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseSite.ThemeWidget, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.ThemeWidget(); }
}
