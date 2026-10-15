package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Base's slider groove span: as its slider groove on its own, by delegation. */
public record SliderGrooveSpan()
        implements CastingStyle<ColorScheme.Base, Base, HouseMarks.SliderGroove, HouseSaying.Span> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.SliderGroove> style() {
        return new StylingRes.Delegation<>(new SliderGroove());
    }
}
