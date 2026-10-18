package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-futurism's implementation of slider-group_adjust: as its component standalone, by delegation. */
public record SliderGroupAdjust() implements RootDelegation<BaseScheme, NeoFuturism, HouseContainers.SliderGroup, HouseDoing.Adjust> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseContainers.SliderGroup, RoleRoot> to() { return new SliderGroup(); }
}
