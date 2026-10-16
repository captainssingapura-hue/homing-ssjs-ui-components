package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The brand in the base layer: the site's own, its mark beside its name, in the colour of what holds it - the way home.
 */
public record Brand() implements CastingStyle<BaseScheme, Base, HouseControls.Brand, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Brand> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }
}
