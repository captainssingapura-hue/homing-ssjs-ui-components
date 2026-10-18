package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** sketchy's implementation of trail: the base's, by delegation. */
public record Trail() implements ParentDelegation<BaseScheme, Base, Sketchy, HouseContainers.Trail, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseContainers.Trail, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.Trail(); }
}
