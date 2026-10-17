package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's look of badge: CSS of its own. */
public record Badge() implements CastingStylePerRole<BaseScheme, Base, HouseText.Badge, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseText.Badge, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.AccentWash());
    }
}
