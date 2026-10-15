package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Base's slider knob thumb: as its slider knob on its own, by delegation. */
public record SliderKnobThumb()
        implements CastingStyle<ColorScheme.Base, Base, HouseControls.SliderKnob, HouseDoing.Thumb> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.SliderKnob> style() {
        return new StylingRes.Delegation<>(new SliderKnob());
    }
}
