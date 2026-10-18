package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** retro-futurism's implementation of close-button_close: as its component standalone, by delegation. */
public record CloseButtonClose() implements RootDelegation<BaseScheme, RetroFuturism, HouseControls.CloseButton, HouseDoing.Close> {
    @Override public CastingImplementation<BaseScheme, RetroFuturism, HouseControls.CloseButton, RoleRoot> to() { return new CloseButton(); }
}
