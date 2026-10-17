package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** retro-futurism's implementation of floating-pane: CSS of its own. */
public record FloatingPane() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseContainers.FloatingPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseContainers.FloatingPane, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.ShadeSolid(), new BaseColours.ShadeDarker(), new BaseColours.GlowWarm(), new BaseColours.GlowStrong(), new BaseColours.GlowFull(), new BaseColours.GlowFlare());
    }
}
