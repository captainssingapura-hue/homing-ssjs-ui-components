package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Glassmorphism's toggle knob thumb: as its toggle knob on its own, by delegation. */
public record ToggleKnobThumb()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseControls.ToggleKnob, HouseDoing.Thumb> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.ToggleKnob> style() {
        return new StylingRes.Delegation<>(new ToggleKnob());
    }
}
