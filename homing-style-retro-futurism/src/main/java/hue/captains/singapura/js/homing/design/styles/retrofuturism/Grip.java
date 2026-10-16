package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The grip in retro-futurism: what the user takes hold of to move the thing it is on. Whole: every property its look
 * has (D9).
 */
public record Grip() implements CastingStyle<BaseScheme, RetroFuturism, HouseControls.Grip, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Grip> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }
}
