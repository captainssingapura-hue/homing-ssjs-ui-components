package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The slider in retro-futurism: the house's value along a scale - its row, off, and the keys' marks; its rail, groove,
 * fill and knob are its parts'. Whole: every property its look has (D9).
 */
public record Slider() implements CastingStyle<BaseScheme, RetroFuturism, HouseControls.Slider, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Slider> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Accent(), new HouseColours.KeysCandidate(), new HouseColours.KeysLent());
    }
}
