package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** NeoBrutalism's readout value: as its readout on its own, by delegation. */
public record ReadoutValue()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Readout, HouseSaying.Value> {
    @Override public StylingRes<BaseScheme, HouseText.Readout> style() {
        return new StylingRes.Delegation<>(new Readout());
    }
}
