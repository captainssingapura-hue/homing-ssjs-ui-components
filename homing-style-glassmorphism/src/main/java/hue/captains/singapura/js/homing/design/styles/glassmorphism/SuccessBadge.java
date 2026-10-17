package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The success badge in glassmorphism: success said by a badge, its ink on its wash. Whole: every property its look has
 * (D9).
 */
public record SuccessBadge()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseText.SuccessBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.SuccessBadge> style() {
        return new StylingRes.CssContent<>(new BaseColours.SuccessInk(), new BaseColours.SuccessWash());
    }
}
