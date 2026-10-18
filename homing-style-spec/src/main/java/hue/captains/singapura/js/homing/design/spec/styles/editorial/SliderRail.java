package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** editorial's implementation of slider-rail: CSS of its own - its content apart, the colours it draws with typed here. */
public record SliderRail() implements CastingStylePerRole<BaseScheme, Editorial, HouseMarks.SliderRail, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseMarks.SliderRail, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
