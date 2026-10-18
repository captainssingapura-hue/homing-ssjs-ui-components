package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** neo-brutalism's implementation of scale-widget: the base's, by delegation. */
public record ScaleWidget() implements ParentDelegation<BaseScheme, Base, NeoBrutalism, HousePreferences.ScaleWidget, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePreferences.ScaleWidget, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.ScaleWidget(); }
}
