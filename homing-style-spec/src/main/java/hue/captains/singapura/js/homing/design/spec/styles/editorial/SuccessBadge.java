package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's implementation of success-badge: CSS of its own. */
public record SuccessBadge() implements CastingStylePerRole<BaseScheme, Editorial, HouseText.SuccessBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseText.SuccessBadge, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.SuccessInk(), new BaseColours.SuccessWash());
    }
}
