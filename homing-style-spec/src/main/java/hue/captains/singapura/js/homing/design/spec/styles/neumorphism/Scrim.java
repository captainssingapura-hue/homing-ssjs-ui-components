package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neumorphism's implementation of scrim: CSS of its own. */
public record Scrim() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseContainers.Scrim, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseContainers.Scrim, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
