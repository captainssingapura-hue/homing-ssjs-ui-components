package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The badge in sketchy: a tag on the accent's wash. Whole: every property its look has (D9). */
public record Badge() implements CastingStyle<BaseScheme, Sketchy, HouseText.Badge, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Badge> style() {
        return new StylingRes.CssContent<>(new BaseColours.AccentWash());
    }
}
