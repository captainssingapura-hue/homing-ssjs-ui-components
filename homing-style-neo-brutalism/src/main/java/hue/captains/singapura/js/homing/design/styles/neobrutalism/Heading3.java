package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 3 in neo-brutalism: a heading at the third level. Whole: every property its look has (D9). */
public record Heading3() implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Heading3, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Heading3> style() {
        return new StylingRes.CssContent<>(new BaseColours.HeadingInk());
    }
}
