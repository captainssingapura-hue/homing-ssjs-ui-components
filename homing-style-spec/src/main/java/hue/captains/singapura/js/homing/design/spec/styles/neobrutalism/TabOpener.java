package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** neo-brutalism's implementation of tab-opener: the base's, by delegation. */
public record TabOpener() implements ParentDelegation<BaseScheme, Base, NeoBrutalism, HousePanes.TabOpener, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.TabOpener, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.TabOpener(); }
}
