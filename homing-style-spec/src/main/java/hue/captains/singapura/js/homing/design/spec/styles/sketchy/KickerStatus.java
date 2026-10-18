package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's implementation of kicker_status: as its component standalone, by delegation. */
public record KickerStatus() implements RootDelegation<BaseScheme, Sketchy, HouseText.Kicker, HouseSaying.Status> {
    @Override public CastingImplementation<BaseScheme, Sketchy, HouseText.Kicker, RoleRoot> to() { return new Kicker(); }
}
