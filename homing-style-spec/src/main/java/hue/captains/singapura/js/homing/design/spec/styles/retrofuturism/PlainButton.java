package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** retro-futurism's implementation of plain-button: CSS of its own. */
public record PlainButton() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.PlainButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.PlainButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.GlowDim(), new BaseColours.GlowMedium(), new BaseColours.GlowStrong(), new BaseColours.GlowFull());
    }
}
