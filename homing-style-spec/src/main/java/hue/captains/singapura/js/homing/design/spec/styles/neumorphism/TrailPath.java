package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neumorphism's implementation of trail_path: the base's, by delegation. */
public record TrailPath() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseContainers.Trail, HouseDoing.Path> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseContainers.Trail, HouseDoing.Path> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TrailPath());
    }
}
