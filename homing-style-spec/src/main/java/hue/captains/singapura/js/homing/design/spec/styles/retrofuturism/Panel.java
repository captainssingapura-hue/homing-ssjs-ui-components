package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** retro-futurism's implementation of panel: CSS of its own. */
public record Panel() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseContainers.Panel, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseContainers.Panel, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.ShadeDarkest(), new BaseColours.GlowWarm());
    }
}
