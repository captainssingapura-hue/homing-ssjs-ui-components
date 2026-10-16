package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** The brand's mark in the base layer: a square of the accent, until a site gives a logo; every style's alike. */
public record BrandMark() implements CastingStyle<BaseScheme, Base, HouseMarks.BrandMark, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.BrandMark> style() {
        return new StylingRes.CssContent<>(new HouseColours.Accent());
    }
}
