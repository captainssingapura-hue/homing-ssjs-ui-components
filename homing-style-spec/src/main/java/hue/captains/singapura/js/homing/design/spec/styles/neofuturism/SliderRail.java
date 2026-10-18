package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** neo-futurism's implementation of slider-rail: CSS of its own - its content apart, the colours it draws with typed here. */
public record SliderRail() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseMarks.SliderRail, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseMarks.SliderRail, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
