package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Base's tick notch: as its tick on its own, by delegation. */
public record TickNotch() implements CastingStyle<ColorScheme.Base, Base, HouseMarks.Tick, HouseSaying.Notch> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Tick> style() {
        return new StylingRes.Delegation<>(new Tick());
    }
}
