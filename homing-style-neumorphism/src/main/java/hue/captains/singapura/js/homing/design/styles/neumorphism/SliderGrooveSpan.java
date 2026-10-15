package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Neumorphism's slider groove span: as its slider groove on its own, by delegation. */
public record SliderGrooveSpan()
        implements CastingStyle<ColorScheme.Base, Neumorphism, HouseMarks.SliderGroove, HouseSaying.Span> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.SliderGroove> style() {
        return new StylingRes.Delegation<>(new SliderGroove());
    }
}
