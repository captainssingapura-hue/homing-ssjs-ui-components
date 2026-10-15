package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The slider rail in the base layer: the stretch a slider's value is set along - its extent, the hand taken anywhere on
 * it. Whole: every property its look has (D9).
 */
public record SliderRail() implements CastingStyle<ColorScheme.Base, Base, HouseMarks.SliderRail, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.SliderRail> style() {
        return new StylingRes.CssContent<>();
    }
}
