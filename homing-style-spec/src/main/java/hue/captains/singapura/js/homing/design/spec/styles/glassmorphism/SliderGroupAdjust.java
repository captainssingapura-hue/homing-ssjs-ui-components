package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** glassmorphism's implementation of slider-group_adjust: as its component standalone, by delegation. */
public record SliderGroupAdjust() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseContainers.SliderGroup, HouseDoing.Adjust> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseContainers.SliderGroup, HouseDoing.Adjust> style() {
        return new StylingRes.RootDelegation<>(new SliderGroup());
    }
}
