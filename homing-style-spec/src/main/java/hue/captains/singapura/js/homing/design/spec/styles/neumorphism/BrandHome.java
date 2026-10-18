package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neumorphism's implementation of brand_home: as its component standalone, by delegation. */
public record BrandHome() implements RootDelegation<BaseScheme, Neumorphism, HouseControls.Brand, HouseDoing.Home> {
    @Override public CastingImplementation<BaseScheme, Neumorphism, HouseControls.Brand, RoleRoot> to() { return new Brand(); }
}
