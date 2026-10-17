package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's implementation of caption_hint: as its component standalone, by delegation. */
public record CaptionHint() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Caption, HouseSaying.Hint> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Caption, HouseSaying.Hint> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
