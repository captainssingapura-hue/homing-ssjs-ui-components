package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The code in editorial: code in a line of words, in a fixed face on a faint wash. Whole: every property its look has
 * (D9).
 */
public record Code() implements CastingStyle<BaseScheme, Editorial, HouseText.Code, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Code> style() {
        return new StylingRes.CssContent<>(new HouseColours.WashFaint());
    }
}
