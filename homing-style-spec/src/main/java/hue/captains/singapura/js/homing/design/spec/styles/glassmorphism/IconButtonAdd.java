package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** glassmorphism's implementation of icon-button_add: as its component standalone, by delegation. */
public record IconButtonAdd() implements RootDelegation<BaseScheme, Glassmorphism, HouseControls.IconButton, HouseDoing.Add> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseControls.IconButton, RoleRoot> to() { return new IconButton(); }
}
