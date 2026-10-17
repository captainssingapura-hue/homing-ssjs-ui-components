package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neo-futurism's implementation of scrim: CSS of its own. */
public record Scrim() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseContainers.Scrim, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseContainers.Scrim, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
