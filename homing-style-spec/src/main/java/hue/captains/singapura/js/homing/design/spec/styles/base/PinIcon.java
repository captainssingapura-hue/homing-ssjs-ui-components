package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** base's implementation of pin-icon: CSS of its own - its content apart, the colours it draws with typed here. */
public record PinIcon() implements CastingStylePerRole<BaseScheme, Base, HouseIcons.PinIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseIcons.PinIcon, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
