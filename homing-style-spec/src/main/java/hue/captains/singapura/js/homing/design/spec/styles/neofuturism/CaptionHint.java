package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's look of caption_hint: as its component on its own, by delegation. */
public record CaptionHint() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Caption, HouseSaying.Hint> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Caption, HouseSaying.Hint> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
