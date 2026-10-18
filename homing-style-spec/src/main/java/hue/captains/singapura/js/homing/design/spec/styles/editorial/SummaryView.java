package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** editorial's implementation of summary-view: the base's, by delegation. */
public record SummaryView() implements ParentDelegation<BaseScheme, Base, Editorial, HouseContainers.SummaryView, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseContainers.SummaryView, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.SummaryView(); }
}
