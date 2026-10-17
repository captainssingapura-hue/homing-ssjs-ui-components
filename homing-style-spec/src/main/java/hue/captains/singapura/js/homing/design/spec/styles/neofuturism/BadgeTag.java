package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's implementation of badge_tag: as its component standalone, by delegation. */
public record BadgeTag() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Badge, HouseSaying.Tag> style() {
        return new StylingRes.RootDelegation<>(new Badge());
    }
}
