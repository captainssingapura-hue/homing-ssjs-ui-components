package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The warning badge in retro-futurism: a warning said by a badge, its ink on its wash. Whole: every property its look
 * has (D9).
 */
public record WarningBadge()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseText.WarningBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.WarningBadge> style() {
        return new StylingRes.CssContent<>(new BaseColours.WarningInk(), new BaseColours.WarningWash());
    }
}
