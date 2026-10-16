package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 5 in editorial: a heading at the fifth level. Whole: every property its look has (D9). */
public record Heading5() implements CastingStyle<BaseScheme, Editorial, HouseText.Heading5, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Heading5> style() {
        return new StylingRes.CssContent<>(new BaseColours.HeadingInk());
    }
}
