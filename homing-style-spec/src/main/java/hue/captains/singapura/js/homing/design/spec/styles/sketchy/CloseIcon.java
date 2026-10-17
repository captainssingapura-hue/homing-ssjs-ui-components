package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** sketchy's look of close-icon: CSS of its own. */
public record CloseIcon() implements CastingStylePerRole<BaseScheme, Sketchy, HouseIcons.CloseIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseIcons.CloseIcon, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
