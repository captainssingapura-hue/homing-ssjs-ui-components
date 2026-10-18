package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** neo-futurism's implementation of tab-opener: the base's, by delegation. */
public record TabOpener() implements ParentDelegation<BaseScheme, Base, NeoFuturism, HousePanes.TabOpener, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.TabOpener, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.TabOpener(); }
}
