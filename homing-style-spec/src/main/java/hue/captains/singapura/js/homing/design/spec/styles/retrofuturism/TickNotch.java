package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** retro-futurism's look of tick_notch: as its component on its own, by delegation. */
public record TickNotch() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseMarks.Tick, HouseSaying.Notch> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseMarks.Tick, HouseSaying.Notch> style() {
        return new StylingRes.RootDelegation<>(new Tick());
    }
}
