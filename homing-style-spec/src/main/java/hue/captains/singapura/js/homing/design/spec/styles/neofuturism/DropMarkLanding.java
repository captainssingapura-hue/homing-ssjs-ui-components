package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neo-futurism's implementation of drop-mark_landing: the base's, by delegation. */
public record DropMarkLanding() implements ParentDelegation<BaseScheme, Base, NeoFuturism, HouseMarks.DropMark, HouseSaying.Landing> {
    @Override public CastingImplementation<BaseScheme, Base, HouseMarks.DropMark, HouseSaying.Landing> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.DropMarkLanding(); }
}
