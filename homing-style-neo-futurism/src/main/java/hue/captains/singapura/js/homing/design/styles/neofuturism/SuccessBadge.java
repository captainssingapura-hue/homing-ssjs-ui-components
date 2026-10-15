package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The success badge in neo-futurism: success said by a badge, its ink on its wash. Whole: every property its look has
 * (D9).
 */
public record SuccessBadge() implements CastingStyle<ColorScheme.Base, NeoFuturism, HouseText.SuccessBadge, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.SuccessBadge> style() {
        return new StylingRes.CssContent<>(new HouseColours.SuccessInk(), new HouseColours.SuccessWash());
    }
}
