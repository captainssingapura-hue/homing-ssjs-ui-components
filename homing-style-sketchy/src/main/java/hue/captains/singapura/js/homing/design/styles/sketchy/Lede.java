package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The lede in sketchy: the line that opens, larger and quieter. Whole: every property its look has (D9). */
public record Lede() implements CastingStyle<ColorScheme.Base, Sketchy, HouseText.Lede, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Lede> style() {
        return new StylingRes.CssContent<>(new HouseColours.TextFaded());
    }
}
