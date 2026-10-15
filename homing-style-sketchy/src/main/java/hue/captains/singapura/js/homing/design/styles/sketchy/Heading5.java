package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 5 in sketchy: a heading at the fifth level. Whole: every property its look has (D9). */
public record Heading5() implements CastingStyle<ColorScheme.Base, Sketchy, HouseText.Heading5, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Heading5> style() {
        return new StylingRes.CssContent<>(new HouseColours.HeadingInk());
    }
}
