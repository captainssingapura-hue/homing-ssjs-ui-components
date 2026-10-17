package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** retro-futurism's implementation of plain-card: CSS of its own. */
public record PlainCard() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseContainers.PlainCard, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseContainers.PlainCard, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Border(), new BaseColours.GlowDim(), new BaseColours.GlowFull());
    }
}
