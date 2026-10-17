package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's implementation of kicker_status: as its component standalone, by delegation. */
public record KickerStatus() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseText.Kicker, HouseSaying.Status> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseText.Kicker, HouseSaying.Status> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
