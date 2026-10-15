package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 3 in editorial: a heading at the third level. Whole: every property its look has (D9). */
public record Heading3() implements CastingStyle<ColorScheme.Base, Editorial, HouseText.Heading3, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Heading3> style() {
        return new StylingRes.CssContent<>(new HouseColours.HeadingInk());
    }
}
