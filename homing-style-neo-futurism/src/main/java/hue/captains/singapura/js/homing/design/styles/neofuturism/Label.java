package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The label in neo-futurism: words that name what they stand by. Whole: every property its look has (D9). */
public record Label() implements CastingStyle<BaseScheme, NeoFuturism, HouseText.Label, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Label> style() {
        return new StylingRes.CssContent<>();
    }
}
