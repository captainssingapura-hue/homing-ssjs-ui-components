package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** sketchy's implementation of tick_notch: as its component standalone, by delegation. */
public record TickNotch() implements CastingStylePerRole<BaseScheme, Sketchy, HouseMarks.Tick, HouseSaying.Notch> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseMarks.Tick, HouseSaying.Notch> style() {
        return new StylingRes.RootDelegation<>(new Tick());
    }
}
