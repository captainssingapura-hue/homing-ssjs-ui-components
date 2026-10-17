package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** base's implementation of range_adjust: as its component standalone, by delegation. */
public record RangeAdjust() implements CastingStylePerRole<BaseScheme, Base, HouseControls.Range, HouseDoing.Adjust> {
    @Override public StylingRes<BaseScheme, Base, HouseControls.Range, HouseDoing.Adjust> style() {
        return new StylingRes.RootDelegation<>(new Range());
    }
}
