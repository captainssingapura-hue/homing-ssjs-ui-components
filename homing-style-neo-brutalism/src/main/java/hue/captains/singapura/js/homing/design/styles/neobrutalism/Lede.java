package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The lede in neo-brutalism: the line that opens, larger and quieter. Whole: every property its look has (D9). */
public record Lede() implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseText.Lede, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Lede> style() {
        return new StylingRes.CssContent<>(new HouseColours.TextFaded());
    }
}
