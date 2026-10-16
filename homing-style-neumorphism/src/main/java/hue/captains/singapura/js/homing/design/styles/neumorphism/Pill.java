package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The pill in neumorphism: a count in a rounded wash. Whole: every property its look has (D9). */
public record Pill() implements CastingStyle<BaseScheme, Neumorphism, HouseText.Pill, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Pill> style() {
        return new StylingRes.CssContent<>(new HouseColours.WashSoft());
    }
}
