package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** base's implementation of slider-group_adjust: as its component standalone, by delegation. */
public record SliderGroupAdjust() implements RootDelegation<BaseScheme, Base, HouseContainers.SliderGroup, HouseDoing.Adjust> {
    @Override public CastingImplementation<BaseScheme, Base, HouseContainers.SliderGroup, RoleRoot> to() { return new SliderGroup(); }
}
