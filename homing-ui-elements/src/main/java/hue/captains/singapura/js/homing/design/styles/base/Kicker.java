package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The kicker in the base layer: the small line set above a title. Whole: every property its look has (D9). */
public record Kicker() implements CastingStyle<ColorScheme.Base, Base, HouseText.Kicker, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Kicker> style() {
        return new StylingRes.CssContent<>(new HouseColours.KickerInk());
    }
}
