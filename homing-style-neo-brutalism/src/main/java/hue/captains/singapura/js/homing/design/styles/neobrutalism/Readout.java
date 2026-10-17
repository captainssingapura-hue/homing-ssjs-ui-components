package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The readout in neo-brutalism: a figure read off, its digits even. Whole: every property its look has (D9). */
public record Readout() implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Readout, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Readout> style() {
        return new StylingRes.CssContent<>();
    }
}
