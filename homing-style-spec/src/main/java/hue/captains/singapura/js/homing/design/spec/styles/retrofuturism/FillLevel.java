package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** retro-futurism's implementation of fill_level: as its component standalone, by delegation. */
public record FillLevel() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseMarks.Fill, HouseSaying.Level> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseMarks.Fill, HouseSaying.Level> style() {
        return new StylingRes.RootDelegation<>(new Fill());
    }
}
