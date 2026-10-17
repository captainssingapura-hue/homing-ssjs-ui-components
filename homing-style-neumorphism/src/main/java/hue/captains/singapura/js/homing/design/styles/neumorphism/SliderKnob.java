package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The slider knob in neumorphism: what is grabbed to move its value - its face, its ring and its hand; stood up, a cap.
 * Whole: every property its look has (D9).
 */
public record SliderKnob() implements CastingStyle<BaseScheme, Neumorphism, HouseControls.SliderKnob, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.SliderKnob> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Accent(), new BaseColours.Ring());
    }
}
