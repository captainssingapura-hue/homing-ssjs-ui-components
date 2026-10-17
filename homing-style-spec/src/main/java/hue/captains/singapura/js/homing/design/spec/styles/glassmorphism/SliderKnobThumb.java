package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** glassmorphism's implementation of slider-knob_thumb: as its component standalone, by delegation. */
public record SliderKnobThumb() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.SliderKnob, HouseDoing.Thumb> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseControls.SliderKnob, HouseDoing.Thumb> style() {
        return new StylingRes.RootDelegation<>(new SliderKnob());
    }
}
