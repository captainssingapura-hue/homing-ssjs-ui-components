package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** base's look of slider-rail: CSS of its own. */
public record SliderRail() implements CastingStylePerRole<BaseScheme, Base, HouseMarks.SliderRail, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseMarks.SliderRail, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
