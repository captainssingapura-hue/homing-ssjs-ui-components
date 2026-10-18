package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** sketchy's implementation of preference-field: the base's, by delegation. */
public record PreferenceField() implements ParentDelegation<BaseScheme, Base, Sketchy, HousePreferences.PreferenceField, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePreferences.PreferenceField, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.PreferenceField(); }
}
