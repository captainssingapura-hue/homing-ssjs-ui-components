package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** glassmorphism's implementation of fill_level: as its component standalone, by delegation. */
public record FillLevel() implements RootDelegation<BaseScheme, Glassmorphism, HouseMarks.Fill, HouseSaying.Level> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseMarks.Fill, RoleRoot> to() { return new Fill(); }
}
