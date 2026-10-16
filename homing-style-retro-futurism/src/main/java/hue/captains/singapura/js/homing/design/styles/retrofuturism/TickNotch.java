package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** RetroFuturism's tick notch: as its tick on its own, by delegation. */
public record TickNotch() implements CastingStyle<BaseScheme, RetroFuturism, HouseMarks.Tick, HouseSaying.Notch> {
    @Override public StylingRes<BaseScheme, HouseMarks.Tick> style() {
        return new StylingRes.Delegation<>(new Tick());
    }
}
