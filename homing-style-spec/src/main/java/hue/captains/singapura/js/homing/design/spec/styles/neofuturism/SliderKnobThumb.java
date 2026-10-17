package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-futurism's implementation of slider-knob_thumb: as its component standalone, by delegation. */
public record SliderKnobThumb() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.SliderKnob, HouseDoing.Thumb> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.SliderKnob, HouseDoing.Thumb> style() {
        return new StylingRes.RootDelegation<>(new SliderKnob());
    }
}
