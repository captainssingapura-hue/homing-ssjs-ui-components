package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 6 in the base layer: a heading at the sixth level. Whole: every property its look has (D9). */
public record Heading6() implements CastingStyle<ColorScheme.Base, Base, HouseText.Heading6, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Heading6> style() {
        return new StylingRes.CssContent<>(new HouseColours.HeadingInk());
    }
}
