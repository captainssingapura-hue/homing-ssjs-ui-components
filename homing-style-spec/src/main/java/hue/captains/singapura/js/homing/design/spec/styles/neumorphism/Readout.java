package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's implementation of readout: CSS of its own - its content apart, the colours it draws with typed here. */
public record Readout() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseText.Readout, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseText.Readout, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
