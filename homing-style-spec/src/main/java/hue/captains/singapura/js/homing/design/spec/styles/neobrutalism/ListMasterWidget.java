package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** neo-brutalism's implementation of list-master-widget: the base's, by delegation. */
public record ListMasterWidget() implements ParentDelegation<BaseScheme, Base, NeoBrutalism, HousePreferences.ListMasterWidget, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePreferences.ListMasterWidget, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.ListMasterWidget(); }
}
