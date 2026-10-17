package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The danger badge in neo-futurism: danger said by a badge, its ink on its wash. Whole: every property its look has
 * (D9).
 */
public record DangerBadge() implements CastingStyle<BaseScheme, NeoFuturism, HouseText.DangerBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.DangerBadge> style() {
        return new StylingRes.CssContent<>(new BaseColours.DangerInk(), new BaseColours.DangerWash());
    }
}
