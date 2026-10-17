package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The trail in the base layer: the way back, crumb by crumb - its words softer than what they are written on, whichever
 * ink that is; every style's alike. Whole: every property its look has (D9).
 */
public record Trail() implements CastingStyle<BaseScheme, Base, HouseContainers.Trail, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.Trail> style() {
        return new StylingRes.CssContent<>(new BaseColours.Soft());
    }
}
