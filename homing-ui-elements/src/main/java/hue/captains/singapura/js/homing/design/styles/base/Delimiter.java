package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The delimiter in the base layer: the sign between a run's steps, on one line with them - in the colour it is given,
 * muted. Whole: every property its look has (D9).
 */
public record Delimiter() implements CastingStyle<BaseScheme, Base, HouseMarks.Delimiter, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.Delimiter> style() {
        return new StylingRes.CssContent<>(new HouseColours.Muted());
    }
}
