package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's look of caption_hint: as its component on its own, by delegation. */
public record CaptionHint() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseText.Caption, HouseSaying.Hint> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseText.Caption, HouseSaying.Hint> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
