package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** glassmorphism's look of slider-rail: CSS of its own. */
public record SliderRail() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseMarks.SliderRail, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseMarks.SliderRail, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
