package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's look of readout: CSS of its own. */
public record Readout() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Readout, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Readout, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
