package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** retro-futurism's implementation of toggle-knob_thumb: as its component standalone, by delegation. */
public record ToggleKnobThumb() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.ToggleKnob, HouseDoing.Thumb> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.ToggleKnob, HouseDoing.Thumb> style() {
        return new StylingRes.RootDelegation<>(new ToggleKnob());
    }
}
