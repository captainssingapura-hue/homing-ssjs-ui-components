package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** glassmorphism's implementation of slider: CSS of its own. */
public record Slider() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.Slider, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseControls.Slider, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }
}
