package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** base's implementation of settings-icon: CSS of its own. */
public record SettingsIcon() implements CastingStylePerRole<BaseScheme, Base, HouseIcons.SettingsIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseIcons.SettingsIcon, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
