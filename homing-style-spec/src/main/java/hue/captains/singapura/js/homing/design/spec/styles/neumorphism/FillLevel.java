package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neumorphism's implementation of fill_level: as its component standalone, by delegation. */
public record FillLevel() implements RootDelegation<BaseScheme, Neumorphism, HouseMarks.Fill, HouseSaying.Level> {
    @Override public CastingImplementation<BaseScheme, Neumorphism, HouseMarks.Fill, RoleRoot> to() { return new Fill(); }
}
