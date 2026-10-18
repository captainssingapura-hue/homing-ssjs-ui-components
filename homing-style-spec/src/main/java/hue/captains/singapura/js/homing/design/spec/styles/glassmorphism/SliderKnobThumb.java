package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** glassmorphism's implementation of slider-knob_thumb: as its component standalone, by delegation. */
public record SliderKnobThumb() implements RootDelegation<BaseScheme, Glassmorphism, HouseControls.SliderKnob, HouseDoing.Thumb> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseControls.SliderKnob, RoleRoot> to() { return new SliderKnob(); }
}
