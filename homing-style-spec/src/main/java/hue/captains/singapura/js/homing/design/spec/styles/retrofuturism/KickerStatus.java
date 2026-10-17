package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's implementation of kicker_status: as its component standalone, by delegation. */
public record KickerStatus() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Kicker, HouseSaying.Status> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Kicker, HouseSaying.Status> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
