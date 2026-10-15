package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The label in sketchy: words that name what they stand by. Whole: every property its look has (D9). */
public record Label() implements CastingStyle<ColorScheme.Base, Sketchy, HouseText.Label, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Label> style() {
        return new StylingRes.CssContent<>();
    }
}
