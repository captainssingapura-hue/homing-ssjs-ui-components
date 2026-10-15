package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The warning badge in neo-brutalism: a warning said by a badge, its ink on its wash. Whole: every property its look
 * has (D9).
 */
public record WarningBadge() implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseText.WarningBadge, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.WarningBadge> style() {
        return new StylingRes.CssContent<>(new HouseColours.WarningInk(), new HouseColours.WarningWash());
    }
}
