package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The code in the base layer: code in a line of words, in a fixed face on a faint wash. Whole: every property its look
 * has (D9).
 */
public record Code() implements CastingStyle<BaseScheme, Base, HouseText.Code, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Code> style() {
        return new StylingRes.CssContent<>(new BaseColours.WashFaint());
    }
}
