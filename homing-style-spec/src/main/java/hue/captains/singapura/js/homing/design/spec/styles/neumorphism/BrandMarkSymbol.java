package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neumorphism's implementation of brand-mark_symbol: the base's, by delegation. */
public record BrandMarkSymbol() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseMarks.BrandMark, HouseSaying.Symbol> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseMarks.BrandMark, HouseSaying.Symbol> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.BrandMarkSymbol());
    }
}
