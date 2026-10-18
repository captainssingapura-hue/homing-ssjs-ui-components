package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's implementation of lede_summary: as its component standalone, by delegation. */
public record LedeSummary() implements RootDelegation<BaseScheme, Base, HouseText.Lede, HouseSaying.Summary> {
    @Override public CastingImplementation<BaseScheme, Base, HouseText.Lede, RoleRoot> to() { return new Lede(); }
}
