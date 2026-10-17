package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neumorphism's implementation of ground: CSS of its own. */
public record Ground() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseContainers.Ground, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseContainers.Ground, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ground(), new BaseColours.ScrollThumb(), new BaseColours.Text());
    }
}
