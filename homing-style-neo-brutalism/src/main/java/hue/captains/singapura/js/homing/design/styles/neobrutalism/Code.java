package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The code in neo-brutalism: code in a line of words, in a fixed face on a faint wash. Whole: every property its look
 * has (D9).
 */
public record Code() implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Code, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Code> style() {
        return new StylingRes.CssContent<>(new HouseColours.WashFaint());
    }
}
