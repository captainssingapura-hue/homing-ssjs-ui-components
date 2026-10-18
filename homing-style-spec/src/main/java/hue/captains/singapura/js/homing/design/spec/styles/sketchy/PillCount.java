package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's implementation of pill_count: as its component standalone, by delegation. */
public record PillCount() implements RootDelegation<BaseScheme, Sketchy, HouseText.Pill, HouseSaying.Count> {
    @Override public CastingImplementation<BaseScheme, Sketchy, HouseText.Pill, RoleRoot> to() { return new Pill(); }
}
