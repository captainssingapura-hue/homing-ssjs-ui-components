package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neumorphism's implementation of detent_rest: as its component standalone, by delegation. */
public record DetentRest() implements RootDelegation<BaseScheme, Neumorphism, HouseMarks.Detent, HouseSaying.Rest> {
    @Override public CastingImplementation<BaseScheme, Neumorphism, HouseMarks.Detent, RoleRoot> to() { return new Detent(); }
}
