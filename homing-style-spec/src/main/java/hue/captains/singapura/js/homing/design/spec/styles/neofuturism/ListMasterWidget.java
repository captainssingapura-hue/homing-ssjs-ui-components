package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** neo-futurism's implementation of list-master-widget: the base's, by delegation. */
public record ListMasterWidget() implements ParentDelegation<BaseScheme, Base, NeoFuturism, HousePreferences.ListMasterWidget, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePreferences.ListMasterWidget, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.ListMasterWidget(); }
}
