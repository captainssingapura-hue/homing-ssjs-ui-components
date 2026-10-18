package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neo-futurism's implementation of fill_level: as its component standalone, by delegation. */
public record FillLevel() implements RootDelegation<BaseScheme, NeoFuturism, HouseMarks.Fill, HouseSaying.Level> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseMarks.Fill, RoleRoot> to() { return new Fill(); }
}
