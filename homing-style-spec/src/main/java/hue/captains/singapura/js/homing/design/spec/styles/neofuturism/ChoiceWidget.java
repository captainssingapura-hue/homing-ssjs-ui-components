package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** neo-futurism's implementation of choice-widget: the base's, by delegation. */
public record ChoiceWidget() implements ParentDelegation<BaseScheme, Base, NeoFuturism, HousePreferences.ChoiceWidget, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePreferences.ChoiceWidget, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.ChoiceWidget(); }
}
