package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** NeoFuturism's slider knob thumb: as its slider knob on its own, by delegation. */
public record SliderKnobThumb()
        implements CastingStyle<BaseScheme, NeoFuturism, HouseControls.SliderKnob, HouseDoing.Thumb> {
    @Override public StylingRes<BaseScheme, HouseControls.SliderKnob> style() {
        return new StylingRes.Delegation<>(new SliderKnob());
    }
}
