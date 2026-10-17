package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** sketchy's implementation of settings-icon: CSS of its own. */
public record SettingsIcon() implements CastingStylePerRole<BaseScheme, Sketchy, HouseIcons.SettingsIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseIcons.SettingsIcon, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
