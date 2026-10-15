package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The readout in neo-futurism: a figure read off, its digits even. Whole: every property its look has (D9). */
public record Readout() implements CastingStyle<ColorScheme.Base, NeoFuturism, HouseText.Readout, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Readout> style() {
        return new StylingRes.CssContent<>();
    }
}
