package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** NeoBrutalism's pill count: as its pill on its own, by delegation. */
public record PillCount() implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Pill, HouseSaying.Count> {
    @Override public StylingRes<BaseScheme, HouseText.Pill> style() {
        return new StylingRes.Delegation<>(new Pill());
    }
}
