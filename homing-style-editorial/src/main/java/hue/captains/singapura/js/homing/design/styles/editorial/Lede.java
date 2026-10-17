package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The lede in editorial: the line that opens, larger and quieter. Whole: every property its look has (D9). */
public record Lede() implements CastingStyle<BaseScheme, Editorial, HouseText.Lede, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Lede> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded());
    }
}
