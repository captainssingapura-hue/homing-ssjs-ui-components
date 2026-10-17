package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-brutalism's implementation of slider-group_adjust: as its component standalone, by delegation. */
public record SliderGroupAdjust() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.SliderGroup, HouseDoing.Adjust> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseContainers.SliderGroup, HouseDoing.Adjust> style() {
        return new StylingRes.RootDelegation<>(new SliderGroup());
    }
}
