package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** retro-futurism's look of scrim: CSS of its own. */
public record Scrim() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseContainers.Scrim, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseContainers.Scrim, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
