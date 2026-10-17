package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's implementation of caption_value: as its component standalone, by delegation. */
public record CaptionValue() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseText.Caption, HouseSaying.Value> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseText.Caption, HouseSaying.Value> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
