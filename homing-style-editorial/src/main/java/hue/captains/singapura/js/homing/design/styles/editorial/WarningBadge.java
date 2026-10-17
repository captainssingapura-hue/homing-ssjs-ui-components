package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The warning badge in editorial: a warning said by a badge, its ink on its wash. Whole: every property its look has
 * (D9).
 */
public record WarningBadge() implements CastingStyle<BaseScheme, Editorial, HouseText.WarningBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.WarningBadge> style() {
        return new StylingRes.CssContent<>(new BaseColours.WarningInk(), new BaseColours.WarningWash());
    }
}
