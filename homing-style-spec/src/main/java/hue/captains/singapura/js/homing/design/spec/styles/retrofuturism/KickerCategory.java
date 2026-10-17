package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's implementation of kicker_category: as its component standalone, by delegation. */
public record KickerCategory() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Kicker, HouseSaying.Category> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Kicker, HouseSaying.Category> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
