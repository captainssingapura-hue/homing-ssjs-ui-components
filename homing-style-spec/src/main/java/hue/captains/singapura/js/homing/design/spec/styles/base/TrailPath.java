package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** base's implementation of trail_path: as its component standalone, by delegation. */
public record TrailPath() implements RootDelegation<BaseScheme, Base, HouseContainers.Trail, HouseDoing.Path> {
    @Override public CastingImplementation<BaseScheme, Base, HouseContainers.Trail, RoleRoot> to() { return new Trail(); }
}
