package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The warning button in retro-futurism: an action that needs a second thought. Whole: every property its look has (D9).
 */
public record WarningButton()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseControls.WarningButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.WarningButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.WarningInk(), new BaseColours.Border(), new BaseColours.Ring(),
                new BaseColours.GlowDim(), new BaseColours.GlowMedium(), new BaseColours.GlowStrong(),
                new BaseColours.GlowFull(), new BaseColours.WarningGround());
    }
}
