package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The pill in glassmorphism: a count in a rounded wash. Whole: every property its look has (D9). */
public record Pill() implements CastingStyle<BaseScheme, Glassmorphism, HouseText.Pill, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Pill> style() {
        return new StylingRes.CssContent<>(new BaseColours.WashSoft());
    }
}
