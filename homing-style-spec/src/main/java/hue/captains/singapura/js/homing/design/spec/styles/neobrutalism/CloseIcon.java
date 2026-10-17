package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** neo-brutalism's look of close-icon: CSS of its own. */
public record CloseIcon() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseIcons.CloseIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseIcons.CloseIcon, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
