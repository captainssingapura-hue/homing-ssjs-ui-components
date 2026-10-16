package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The pill in the base layer: a count in a rounded wash. Whole: every property its look has (D9). */
public record Pill() implements CastingStyle<BaseScheme, Base, HouseText.Pill, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Pill> style() {
        return new StylingRes.CssContent<>(new HouseColours.WashSoft());
    }
}
