package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** base's implementation of drop-mark_landing: as its component standalone, by delegation. */
public record DropMarkLanding() implements RootDelegation<BaseScheme, Base, HouseMarks.DropMark, HouseSaying.Landing> {
    @Override public CastingImplementation<BaseScheme, Base, HouseMarks.DropMark, RoleRoot> to() { return new DropMark(); }
}
