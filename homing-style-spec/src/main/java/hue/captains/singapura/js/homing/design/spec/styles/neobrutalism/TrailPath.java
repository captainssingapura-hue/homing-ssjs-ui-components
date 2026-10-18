package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-brutalism's implementation of trail_path: the base's, by delegation. */
public record TrailPath() implements ParentDelegation<BaseScheme, Base, NeoBrutalism, HouseContainers.Trail, HouseDoing.Path> {
    @Override public CastingImplementation<BaseScheme, Base, HouseContainers.Trail, HouseDoing.Path> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.TrailPath(); }
}
