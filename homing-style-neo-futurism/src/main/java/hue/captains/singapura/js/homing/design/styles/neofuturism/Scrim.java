package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The scrim in neo-futurism: the veil over the page behind a dialog - nothing of its own drawn, what is behind it
 * filtered the style's way. Whole: every property its look has (D9).
 */
public record Scrim() implements CastingStyle<BaseScheme, NeoFuturism, HouseContainers.Scrim, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.Scrim> style() {
        return new StylingRes.CssContent<>();
    }
}
