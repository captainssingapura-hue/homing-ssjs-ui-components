package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** base's implementation of primary-button_go: as its component standalone, by delegation. */
public record PrimaryButtonGo() implements RootDelegation<BaseScheme, Base, HouseControls.PrimaryButton, HouseDoing.Go> {
    @Override public CastingImplementation<BaseScheme, Base, HouseControls.PrimaryButton, RoleRoot> to() { return new PrimaryButton(); }
}
