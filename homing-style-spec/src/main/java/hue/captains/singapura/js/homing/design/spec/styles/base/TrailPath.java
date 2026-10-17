package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** base's implementation of trail_path: as its component standalone, by delegation. */
public record TrailPath() implements CastingStylePerRole<BaseScheme, Base, HouseContainers.Trail, HouseDoing.Path> {
    @Override public StylingRes<BaseScheme, Base, HouseContainers.Trail, HouseDoing.Path> style() {
        return new StylingRes.RootDelegation<>(new Trail());
    }
}
