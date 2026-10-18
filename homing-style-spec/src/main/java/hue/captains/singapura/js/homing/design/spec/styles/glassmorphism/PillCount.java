package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's implementation of pill_count: as its component standalone, by delegation. */
public record PillCount() implements RootDelegation<BaseScheme, Glassmorphism, HouseText.Pill, HouseSaying.Count> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseText.Pill, RoleRoot> to() { return new Pill(); }
}
