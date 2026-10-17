package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;

/** sketchy's implementation of theme-widget: the base's, by delegation. */
public record ThemeWidget() implements CastingStylePerRole<BaseScheme, Sketchy, HouseSite.ThemeWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseSite.ThemeWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ThemeWidget());
    }
}
