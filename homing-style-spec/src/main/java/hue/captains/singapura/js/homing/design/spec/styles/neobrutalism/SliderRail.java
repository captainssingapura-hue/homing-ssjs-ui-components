package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** neo-brutalism's implementation of slider-rail: CSS of its own - its content apart, the colours it draws with typed here. */
public record SliderRail() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseMarks.SliderRail, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseMarks.SliderRail, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
