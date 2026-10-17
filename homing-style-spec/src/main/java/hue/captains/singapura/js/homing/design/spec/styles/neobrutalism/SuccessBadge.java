package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-brutalism's implementation of success-badge: CSS of its own. */
public record SuccessBadge() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseText.SuccessBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseText.SuccessBadge, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.SuccessInk(), new BaseColours.SuccessWash());
    }
}
