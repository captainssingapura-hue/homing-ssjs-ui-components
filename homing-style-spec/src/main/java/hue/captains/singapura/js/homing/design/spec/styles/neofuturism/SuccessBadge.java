package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's implementation of success-badge: CSS of its own. */
public record SuccessBadge() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.SuccessBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.SuccessBadge, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.SuccessInk(), new BaseColours.SuccessWash());
    }
}
