package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neo-brutalism's implementation of summary-view: the base's, by delegation. */
public record SummaryView() implements ParentDelegation<BaseScheme, Base, NeoBrutalism, HouseContainers.SummaryView, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseContainers.SummaryView, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.SummaryView(); }
}
