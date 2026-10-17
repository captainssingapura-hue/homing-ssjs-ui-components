package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** NeoBrutalism's detent rest: as its detent on its own, by delegation. */
public record DetentRest()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseMarks.Detent, HouseSaying.Rest> {
    @Override public StylingRes<BaseScheme, HouseMarks.Detent> style() {
        return new StylingRes.Delegation<>(new Detent());
    }
}
