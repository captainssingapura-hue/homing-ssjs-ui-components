package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's look of readout_value: CSS of its own. */
public record ReadoutValue() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Readout, HouseSaying.Value> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Readout, HouseSaying.Value> style() {
        return new StylingRes.CssContent<>();
    }
}
