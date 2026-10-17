package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Neumorphism's toggle knob thumb: as its toggle knob on its own, by delegation. */
public record ToggleKnobThumb()
        implements CastingStyle<BaseScheme, Neumorphism, HouseControls.ToggleKnob, HouseDoing.Thumb> {
    @Override public StylingRes<BaseScheme, HouseControls.ToggleKnob> style() {
        return new StylingRes.Delegation<>(new ToggleKnob());
    }
}
