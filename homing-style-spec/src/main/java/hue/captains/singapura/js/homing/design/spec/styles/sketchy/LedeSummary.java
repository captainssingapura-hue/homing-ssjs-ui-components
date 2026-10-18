package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's implementation of lede_summary: as its component standalone, by delegation. */
public record LedeSummary() implements RootDelegation<BaseScheme, Sketchy, HouseText.Lede, HouseSaying.Summary> {
    @Override public CastingImplementation<BaseScheme, Sketchy, HouseText.Lede, RoleRoot> to() { return new Lede(); }
}
