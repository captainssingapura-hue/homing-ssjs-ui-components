package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-futurism's implementation of switch_toggle: as its component standalone, by delegation. */
public record SwitchToggle() implements RootDelegation<BaseScheme, NeoFuturism, HouseControls.Switch, HouseDoing.Toggle> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseControls.Switch, RoleRoot> to() { return new Switch(); }
}
