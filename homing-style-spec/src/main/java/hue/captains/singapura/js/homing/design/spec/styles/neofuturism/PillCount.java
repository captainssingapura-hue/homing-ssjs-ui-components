package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's implementation of pill_count: as its component standalone, by delegation. */
public record PillCount() implements RootDelegation<BaseScheme, NeoFuturism, HouseText.Pill, HouseSaying.Count> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseText.Pill, RoleRoot> to() { return new Pill(); }
}
