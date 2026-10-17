package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neumorphism's look of slider-knob_thumb: as its component on its own, by delegation. */
public record SliderKnobThumb() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.SliderKnob, HouseDoing.Thumb> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.SliderKnob, HouseDoing.Thumb> style() {
        return new StylingRes.RootDelegation<>(new SliderKnob());
    }
}
