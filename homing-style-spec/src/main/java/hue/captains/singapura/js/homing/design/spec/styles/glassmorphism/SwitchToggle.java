package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** glassmorphism's implementation of switch_toggle: as its component standalone, by delegation. */
public record SwitchToggle() implements RootDelegation<BaseScheme, Glassmorphism, HouseControls.Switch, HouseDoing.Toggle> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseControls.Switch, RoleRoot> to() { return new Switch(); }
}
