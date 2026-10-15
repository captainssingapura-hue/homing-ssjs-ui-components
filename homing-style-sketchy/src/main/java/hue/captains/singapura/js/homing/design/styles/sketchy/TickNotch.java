package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Sketchy's tick notch: as its tick on its own, by delegation. */
public record TickNotch() implements CastingStyle<ColorScheme.Base, Sketchy, HouseMarks.Tick, HouseSaying.Notch> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Tick> style() {
        return new StylingRes.Delegation<>(new Tick());
    }
}
