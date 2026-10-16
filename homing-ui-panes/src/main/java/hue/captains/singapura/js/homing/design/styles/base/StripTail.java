package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The strip's tail in the base layer: the end of a strip of tabs - the room its count and its close sit in, round them
 * and between; every style's alike. Whole: every property its look has (D9).
 */
public record StripTail() implements CastingStyle<BaseScheme, Base, HouseRegions.StripTail, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseRegions.StripTail> style() {
        return new StylingRes.CssContent<>();
    }
}
