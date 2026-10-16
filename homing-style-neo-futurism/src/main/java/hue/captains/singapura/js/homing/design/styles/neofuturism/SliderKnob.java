package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The slider knob in neo-futurism: what is grabbed to move its value - its face, its ring and its hand; stood up, a
 * cap. Whole: every property its look has (D9).
 */
public record SliderKnob() implements CastingStyle<BaseScheme, NeoFuturism, HouseControls.SliderKnob, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.SliderKnob> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }
}
