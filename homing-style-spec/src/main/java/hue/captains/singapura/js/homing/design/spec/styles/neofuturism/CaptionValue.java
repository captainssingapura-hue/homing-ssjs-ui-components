package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's implementation of caption_value: as its component standalone, by delegation. */
public record CaptionValue() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Caption, HouseSaying.Value> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Caption, HouseSaying.Value> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
