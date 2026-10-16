package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The lede in glassmorphism: the line that opens, larger and quieter. Whole: every property its look has (D9). */
public record Lede() implements CastingStyle<BaseScheme, Glassmorphism, HouseText.Lede, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Lede> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded());
    }
}
