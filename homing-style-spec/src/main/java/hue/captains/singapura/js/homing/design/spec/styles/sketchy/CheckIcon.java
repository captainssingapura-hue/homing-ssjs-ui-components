package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** sketchy's implementation of check-icon: CSS of its own - its content apart, the colours it draws with typed here. */
public record CheckIcon() implements CastingStylePerRole<BaseScheme, Sketchy, HouseIcons.CheckIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseIcons.CheckIcon, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
