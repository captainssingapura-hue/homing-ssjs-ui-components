package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The slider knob in sketchy: what is grabbed to move its value - its face, its ring and its hand; stood up, a cap.
 * Whole: every property its look has (D9).
 */
public record SliderKnob() implements CastingStyle<BaseScheme, Sketchy, HouseControls.SliderKnob, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.SliderKnob> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Accent(), new HouseColours.Ring());
    }
}
