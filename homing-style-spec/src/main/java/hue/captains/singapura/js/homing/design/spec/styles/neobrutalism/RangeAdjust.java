package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-brutalism's implementation of range_adjust: as its component standalone, by delegation. */
public record RangeAdjust() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.Range, HouseDoing.Adjust> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseControls.Range, HouseDoing.Adjust> style() {
        return new StylingRes.RootDelegation<>(new Range());
    }
}
