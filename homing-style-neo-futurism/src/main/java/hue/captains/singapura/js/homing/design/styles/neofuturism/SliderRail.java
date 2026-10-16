package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The slider rail in neo-futurism: the stretch a slider's value is set along - its extent, the hand taken anywhere on
 * it. Whole: every property its look has (D9).
 */
public record SliderRail() implements CastingStyle<BaseScheme, NeoFuturism, HouseMarks.SliderRail, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.SliderRail> style() {
        return new StylingRes.CssContent<>();
    }
}
