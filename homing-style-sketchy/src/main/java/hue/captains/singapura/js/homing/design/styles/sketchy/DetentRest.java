package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Sketchy's detent rest: as its detent on its own, by delegation. */
public record DetentRest() implements CastingStyle<ColorScheme.Base, Sketchy, HouseMarks.Detent, HouseSaying.Rest> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Detent> style() {
        return new StylingRes.Delegation<>(new Detent());
    }
}
