package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The label in editorial: words that name what they stand by. Whole: every property its look has (D9). */
public record Label() implements CastingStyle<BaseScheme, Editorial, HouseText.Label, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Label> style() {
        return new StylingRes.CssContent<>();
    }
}
