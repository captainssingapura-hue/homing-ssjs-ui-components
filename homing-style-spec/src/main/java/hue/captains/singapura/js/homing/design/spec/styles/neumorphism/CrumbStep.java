package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neumorphism's implementation of crumb_step: as its component standalone, by delegation. */
public record CrumbStep() implements RootDelegation<BaseScheme, Neumorphism, HouseControls.Crumb, HouseDoing.Step> {
    @Override public CastingImplementation<BaseScheme, Neumorphism, HouseControls.Crumb, RoleRoot> to() { return new Crumb(); }
}
