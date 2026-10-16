package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The warning badge in neo-futurism: a warning said by a badge, its ink on its wash. Whole: every property its look has
 * (D9).
 */
public record WarningBadge() implements CastingStyle<BaseScheme, NeoFuturism, HouseText.WarningBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.WarningBadge> style() {
        return new StylingRes.CssContent<>(new HouseColours.WarningInk(), new HouseColours.WarningWash());
    }
}
