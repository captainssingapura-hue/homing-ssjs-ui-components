package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** retro-futurism's look of toggle-knob: CSS of its own. */
public record ToggleKnob() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.ToggleKnob, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.ToggleKnob, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised());
    }
}
