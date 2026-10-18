package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** editorial's implementation of toggle-knob_thumb: as its component standalone, by delegation. */
public record ToggleKnobThumb() implements RootDelegation<BaseScheme, Editorial, HouseControls.ToggleKnob, HouseDoing.Thumb> {
    @Override public CastingImplementation<BaseScheme, Editorial, HouseControls.ToggleKnob, RoleRoot> to() { return new ToggleKnob(); }
}
