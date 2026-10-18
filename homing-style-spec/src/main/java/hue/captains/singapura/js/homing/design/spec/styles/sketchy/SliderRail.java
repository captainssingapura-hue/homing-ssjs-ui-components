package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** sketchy's implementation of slider-rail: CSS of its own - its content apart, the colours it draws with typed here. */
public record SliderRail() implements CastingStylePerRole<BaseScheme, Sketchy, HouseMarks.SliderRail, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseMarks.SliderRail, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
