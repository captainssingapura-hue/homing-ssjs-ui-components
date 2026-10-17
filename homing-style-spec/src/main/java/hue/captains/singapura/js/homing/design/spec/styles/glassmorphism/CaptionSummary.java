package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's implementation of caption_summary: as its component standalone, by delegation. */
public record CaptionSummary() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseText.Caption, HouseSaying.Summary> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseText.Caption, HouseSaying.Summary> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
