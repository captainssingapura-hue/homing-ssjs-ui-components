package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** An icon as a symbol - an icon button's face: the colour of what holds it, whole (D30). */
public record IconSymbol() implements CastingStyle<BaseScheme, Base, HouseMarks.Icon, HouseSaying.Symbol> {
    @Override public StylingRes<BaseScheme, HouseMarks.Icon> style() {
        return new StylingRes.CssContent<>();
    }
}
