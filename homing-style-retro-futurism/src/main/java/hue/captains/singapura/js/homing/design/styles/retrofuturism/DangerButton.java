package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The danger button in retro-futurism: an action that destroys or cannot be undone: danger at 1, safety turned the
 * other way. Whole: every property its look has (D9).
 */
public record DangerButton()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseControls.DangerButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.DangerButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Ring(), new BaseColours.GlowDim(), new BaseColours.GlowMedium(),
                new BaseColours.GlowStrong(), new BaseColours.GlowFull(), new BaseColours.DangerEdge(),
                new BaseColours.DangerGround(), new BaseColours.DangerWriting());
    }
}
