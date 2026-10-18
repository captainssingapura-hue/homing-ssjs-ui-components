package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** glassmorphism's implementation of brand-mark_symbol: the base's, by delegation. */
public record BrandMarkSymbol() implements ParentDelegation<BaseScheme, Base, Glassmorphism, HouseMarks.BrandMark, HouseSaying.Symbol> {
    @Override public CastingImplementation<BaseScheme, Base, HouseMarks.BrandMark, HouseSaying.Symbol> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.BrandMarkSymbol(); }
}
