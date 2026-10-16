package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The brand in retro-futurism: the site's own, its mark beside its name, in the colour of what holds it - the way home.
 */
public record Brand() implements CastingStyle<BaseScheme, RetroFuturism, HouseControls.Brand, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Brand> style() {
        return new StylingRes.CssContent<>(new HouseColours.Ring());
    }
}
