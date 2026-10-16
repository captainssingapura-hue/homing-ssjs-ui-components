package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The lede in neumorphism: the line that opens, larger and quieter. Whole: every property its look has (D9). */
public record Lede() implements CastingStyle<BaseScheme, Neumorphism, HouseText.Lede, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Lede> style() {
        return new StylingRes.CssContent<>(new HouseColours.TextFaded());
    }
}
