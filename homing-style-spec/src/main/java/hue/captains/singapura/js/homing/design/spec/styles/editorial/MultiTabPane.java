package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** editorial's implementation of multi-tab-pane: the base's, by delegation. */
public record MultiTabPane() implements ParentDelegation<BaseScheme, Base, Editorial, HousePanes.MultiTabPane, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.MultiTabPane, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.MultiTabPane(); }
}
