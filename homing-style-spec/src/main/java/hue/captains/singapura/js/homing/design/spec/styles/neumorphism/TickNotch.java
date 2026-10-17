package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neumorphism's implementation of tick_notch: as its component standalone, by delegation. */
public record TickNotch() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseMarks.Tick, HouseSaying.Notch> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseMarks.Tick, HouseSaying.Notch> style() {
        return new StylingRes.RootDelegation<>(new Tick());
    }
}
