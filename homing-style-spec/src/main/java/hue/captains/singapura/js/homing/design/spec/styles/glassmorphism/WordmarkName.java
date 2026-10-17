package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's implementation of wordmark_name: as its component standalone, by delegation. */
public record WordmarkName() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseText.Wordmark, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseText.Wordmark, HouseSaying.Name> style() {
        return new StylingRes.RootDelegation<>(new Wordmark());
    }
}
