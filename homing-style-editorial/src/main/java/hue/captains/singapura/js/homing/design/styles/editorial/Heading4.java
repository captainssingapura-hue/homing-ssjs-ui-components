package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 4 in editorial: a heading at the fourth level. Whole: every property its look has (D9). */
public record Heading4() implements CastingStyle<ColorScheme.Base, Editorial, HouseText.Heading4, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Heading4> style() {
        return new StylingRes.CssContent<>(new HouseColours.HeadingInk());
    }
}
