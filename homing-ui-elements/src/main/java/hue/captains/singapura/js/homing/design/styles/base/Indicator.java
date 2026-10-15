package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The indicator in the base layer: a small sign of a state - nothing of its own drawn yet: its holder places it. Whole:
 * every property its look has (D9).
 */
public record Indicator() implements CastingStyle<ColorScheme.Base, Base, HouseMarks.Indicator, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Indicator> style() {
        return new StylingRes.CssContent<>();
    }
}
