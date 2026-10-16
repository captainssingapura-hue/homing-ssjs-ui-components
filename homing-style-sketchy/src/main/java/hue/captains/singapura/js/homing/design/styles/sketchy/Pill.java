package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The pill in sketchy: a count in a rounded wash. Whole: every property its look has (D9). */
public record Pill() implements CastingStyle<BaseScheme, Sketchy, HouseText.Pill, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Pill> style() {
        return new StylingRes.CssContent<>(new HouseColours.WashSoft());
    }
}
