package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** sketchy's implementation of plain-button_reset: as its component standalone, by delegation. */
public record PlainButtonReset() implements RootDelegation<BaseScheme, Sketchy, HouseControls.PlainButton, HouseDoing.Reset> {
    @Override public CastingImplementation<BaseScheme, Sketchy, HouseControls.PlainButton, RoleRoot> to() { return new PlainButton(); }
}
