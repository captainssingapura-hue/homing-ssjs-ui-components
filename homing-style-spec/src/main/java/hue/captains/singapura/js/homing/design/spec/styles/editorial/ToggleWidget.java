package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** editorial's implementation of toggle-widget: the base's, by delegation. */
public record ToggleWidget() implements ParentDelegation<BaseScheme, Base, Editorial, HousePreferences.ToggleWidget, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePreferences.ToggleWidget, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.ToggleWidget(); }
}
