package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** sketchy's implementation of add-tab: the base's, by delegation. */
public record AddTab() implements ParentDelegation<BaseScheme, Base, Sketchy, HousePanes.AddTab, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.AddTab, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.AddTab(); }
}
