package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The success button in neo-futurism: an action that confirms what went well. Whole: every property its look has (D9).
 */
public record SuccessButton()
        implements CastingStyle<BaseScheme, NeoFuturism, HouseControls.SuccessButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.SuccessButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.SuccessInk(), new BaseColours.Border(), new BaseColours.Ring(),
                new BaseColours.ShadeSoft(), new BaseColours.GlowFaint(), new BaseColours.GlowDim(),
                new BaseColours.GlowSoft(), new BaseColours.GlowBright(), new BaseColours.SuccessGround());
    }
}
