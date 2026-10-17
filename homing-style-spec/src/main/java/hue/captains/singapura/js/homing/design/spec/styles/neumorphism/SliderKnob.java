package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** neumorphism's look of slider-knob: CSS of its own. */
public record SliderKnob() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.SliderKnob, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.SliderKnob, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Accent(), new BaseColours.Ring());
    }
}
