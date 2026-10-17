package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** neo-futurism's look of strip-tail: base's, by delegation. */
public record StripTail() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseRegions.StripTail, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseRegions.StripTail, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.StripTail());
    }
}
