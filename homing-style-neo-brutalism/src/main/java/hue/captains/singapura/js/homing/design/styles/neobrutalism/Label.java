package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The label in neo-brutalism: words that name what they stand by. Whole: every property its look has (D9). */
public record Label() implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Label, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Label> style() {
        return new StylingRes.CssContent<>();
    }
}
