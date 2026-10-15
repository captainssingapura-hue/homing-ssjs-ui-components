package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Neumorphism's fill level: as its fill on its own, by delegation. */
public record FillLevel() implements CastingStyle<ColorScheme.Base, Neumorphism, HouseMarks.Fill, HouseSaying.Level> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Fill> style() {
        return new StylingRes.Delegation<>(new Fill());
    }
}
