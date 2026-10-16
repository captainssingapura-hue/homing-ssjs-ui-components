package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The slider in glassmorphism: the house's value along a scale - its row, off, and the keys' marks; its rail, groove,
 * fill and knob are its parts'. Whole: every property its look has (D9).
 */
public record Slider() implements CastingStyle<BaseScheme, Glassmorphism, HouseControls.Slider, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Slider> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Accent(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }
}
