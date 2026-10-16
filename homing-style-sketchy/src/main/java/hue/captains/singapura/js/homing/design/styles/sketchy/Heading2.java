package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 2 in sketchy: a heading at the outline's second level. Whole: every property its look has (D9). */
public record Heading2() implements CastingStyle<BaseScheme, Sketchy, HouseText.Heading2, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Heading2> style() {
        return new StylingRes.CssContent<>(new HouseColours.HeadingInk());
    }
}
