package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The badge in neo-brutalism: a tag on the accent's wash. Whole: every property its look has (D9). */
public record Badge() implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Badge, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Badge> style() {
        return new StylingRes.CssContent<>(new BaseColours.AccentWash());
    }
}
