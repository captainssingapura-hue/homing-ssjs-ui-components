package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The caption in sketchy: a quieter line beside what it tells of. Whole: every property its look has (D9). */
public record Caption() implements CastingStyle<BaseScheme, Sketchy, HouseText.Caption, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Caption> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded());
    }
}
