package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The icon in the base layer: a word of the lexicon, its glyph in the colour it is given; every style's alike. Whole:
 * every property its look has (D9).
 */
public record Icon() implements CastingStyle<BaseScheme, Base, HouseMarks.Icon, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.Icon> style() {
        return new StylingRes.CssContent<>();
    }
}
