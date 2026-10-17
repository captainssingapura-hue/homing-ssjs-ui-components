package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's implementation of wordmark: CSS of its own. */
public record Wordmark() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Wordmark, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Wordmark, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
