package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's look of readout_value: CSS of its own. */
public record ReadoutValue() implements CastingStylePerRole<BaseScheme, Editorial, HouseText.Readout, HouseSaying.Value> {
    @Override public StylingRes<BaseScheme, Editorial, HouseText.Readout, HouseSaying.Value> style() {
        return new StylingRes.CssContent<>();
    }
}
