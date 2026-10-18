package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** editorial's implementation of range_adjust: as its component standalone, by delegation. */
public record RangeAdjust() implements RootDelegation<BaseScheme, Editorial, HouseControls.Range, HouseDoing.Adjust> {
    @Override public CastingImplementation<BaseScheme, Editorial, HouseControls.Range, RoleRoot> to() { return new Range(); }
}
