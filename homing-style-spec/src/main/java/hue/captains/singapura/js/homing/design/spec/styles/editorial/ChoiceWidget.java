package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** editorial's implementation of choice-widget: the base's, by delegation. */
public record ChoiceWidget() implements ParentDelegation<BaseScheme, Base, Editorial, HousePreferences.ChoiceWidget, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePreferences.ChoiceWidget, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.ChoiceWidget(); }
}
