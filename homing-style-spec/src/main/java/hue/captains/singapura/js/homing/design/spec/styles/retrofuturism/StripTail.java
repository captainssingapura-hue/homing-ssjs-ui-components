package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** retro-futurism's look of strip-tail: base's, by delegation. */
public record StripTail() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseRegions.StripTail, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseRegions.StripTail, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.StripTail());
    }
}
