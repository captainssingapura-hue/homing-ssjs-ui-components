package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** editorial's implementation of toggle-knob_thumb: as its component standalone, by delegation. */
public record ToggleKnobThumb() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.ToggleKnob, HouseDoing.Thumb> {
    @Override public StylingRes<BaseScheme, Editorial, HouseControls.ToggleKnob, HouseDoing.Thumb> style() {
        return new StylingRes.RootDelegation<>(new ToggleKnob());
    }
}
