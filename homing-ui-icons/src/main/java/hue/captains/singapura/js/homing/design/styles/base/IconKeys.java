package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/**
 * The icon as keys in the base layer: the mark that says where the keys are - the colour it is given at rest, the
 * accent carried in by its degree, part of the way on a bar and whole in a tab; every style's alike. Whole: every
 * property its look has (D9).
 */
public record IconKeys() implements CastingStyle<BaseScheme, Base, HouseMarks.Icon, HouseSaying.Keys> {
    @Override public StylingRes<BaseScheme, HouseMarks.Icon> style() {
        return new StylingRes.CssContent<>(new BaseColours.KeysMark());
    }
}
