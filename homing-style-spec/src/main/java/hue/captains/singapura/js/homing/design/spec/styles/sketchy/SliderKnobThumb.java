package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** sketchy's implementation of slider-knob_thumb: as its component standalone, by delegation. */
public record SliderKnobThumb() implements CastingStylePerRole<BaseScheme, Sketchy, HouseControls.SliderKnob, HouseDoing.Thumb> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseControls.SliderKnob, HouseDoing.Thumb> style() {
        return new StylingRes.RootDelegation<>(new SliderKnob());
    }
}
