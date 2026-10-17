package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** neumorphism's implementation of slider-rail: CSS of its own. */
public record SliderRail() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseMarks.SliderRail, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseMarks.SliderRail, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
