package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** retro-futurism's look of slider-rail: CSS of its own. */
public record SliderRail() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseMarks.SliderRail, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseMarks.SliderRail, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
