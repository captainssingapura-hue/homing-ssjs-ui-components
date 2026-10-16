package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The drop mark in the base layer: where a dragged thing would land - a bar of the accent. Whole: every property its
 * look has (D9).
 */
public record DropMark() implements CastingStyle<BaseScheme, Base, HouseMarks.DropMark, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.DropMark> style() {
        return new StylingRes.CssContent<>(new HouseColours.Accent());
    }
}
