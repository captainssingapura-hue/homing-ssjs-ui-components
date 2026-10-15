package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** NeoBrutalism's slider knob thumb: as its slider knob on its own, by delegation. */
public record SliderKnobThumb()
        implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseControls.SliderKnob, HouseDoing.Thumb> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.SliderKnob> style() {
        return new StylingRes.Delegation<>(new SliderKnob());
    }
}
