package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** sketchy's implementation of slider-group_adjust: as its component standalone, by delegation. */
public record SliderGroupAdjust() implements RootDelegation<BaseScheme, Sketchy, HouseContainers.SliderGroup, HouseDoing.Adjust> {
    @Override public CastingImplementation<BaseScheme, Sketchy, HouseContainers.SliderGroup, RoleRoot> to() { return new SliderGroup(); }
}
