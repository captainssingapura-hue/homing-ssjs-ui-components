package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neo-futurism's implementation of detent_rest: as its component standalone, by delegation. */
public record DetentRest() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseMarks.Detent, HouseSaying.Rest> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseMarks.Detent, HouseSaying.Rest> style() {
        return new StylingRes.RootDelegation<>(new Detent());
    }
}
