package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The slider knob in the base layer: what is grabbed to move its value - its face, its ring and its hand; stood up, a
 * cap. Whole: every property its look has (D9).
 */
public record SliderKnob() implements CastingStyle<ColorScheme.Base, Base, HouseControls.SliderKnob, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.SliderKnob> style() {
        return new StylingRes.CssContent<>(new HouseColours.Ring());
    }
}
