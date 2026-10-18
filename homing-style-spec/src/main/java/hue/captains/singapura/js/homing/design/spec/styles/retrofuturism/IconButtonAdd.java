package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** retro-futurism's implementation of icon-button_add: as its component standalone, by delegation. */
public record IconButtonAdd() implements RootDelegation<BaseScheme, RetroFuturism, HouseControls.IconButton, HouseDoing.Add> {
    @Override public CastingImplementation<BaseScheme, RetroFuturism, HouseControls.IconButton, RoleRoot> to() { return new IconButton(); }
}
