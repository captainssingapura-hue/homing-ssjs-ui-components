package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's implementation of pill_count: as its component standalone, by delegation. */
public record PillCount() implements CastingStylePerRole<BaseScheme, Base, HouseText.Pill, HouseSaying.Count> {
    @Override public StylingRes<BaseScheme, Base, HouseText.Pill, HouseSaying.Count> style() {
        return new StylingRes.RootDelegation<>(new Pill());
    }
}
