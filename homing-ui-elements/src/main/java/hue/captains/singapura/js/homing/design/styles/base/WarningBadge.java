package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The warning badge in the base layer: a warning said by a badge, its ink on its wash. Whole: every property its look
 * has (D9).
 */
public record WarningBadge() implements CastingStyle<BaseScheme, Base, HouseText.WarningBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.WarningBadge> style() {
        return new StylingRes.CssContent<>(new BaseColours.WarningInk(), new BaseColours.WarningWash());
    }
}
