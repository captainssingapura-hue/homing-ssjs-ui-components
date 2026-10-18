package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's implementation of readout: CSS of its own - its content apart, the colours it draws with typed here. */
public record Readout() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Readout, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Readout, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
