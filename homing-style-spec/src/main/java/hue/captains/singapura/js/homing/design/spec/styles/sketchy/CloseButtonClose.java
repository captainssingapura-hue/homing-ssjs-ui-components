package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** sketchy's implementation of close-button_close: as its component standalone, by delegation. */
public record CloseButtonClose() implements RootDelegation<BaseScheme, Sketchy, HouseControls.CloseButton, HouseDoing.Close> {
    @Override public CastingImplementation<BaseScheme, Sketchy, HouseControls.CloseButton, RoleRoot> to() { return new CloseButton(); }
}
