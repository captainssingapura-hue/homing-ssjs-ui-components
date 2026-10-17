package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's look of lede_summary: as its component on its own, by delegation. */
public record LedeSummary() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Lede, HouseSaying.Summary> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Lede, HouseSaying.Summary> style() {
        return new StylingRes.RootDelegation<>(new Lede());
    }
}
