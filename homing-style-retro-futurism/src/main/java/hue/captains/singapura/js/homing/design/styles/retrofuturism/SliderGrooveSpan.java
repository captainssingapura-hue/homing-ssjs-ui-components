package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** RetroFuturism's slider groove span: as its slider groove on its own, by delegation. */
public record SliderGrooveSpan()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseMarks.SliderGroove, HouseSaying.Span> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.SliderGroove> style() {
        return new StylingRes.Delegation<>(new SliderGroove());
    }
}
