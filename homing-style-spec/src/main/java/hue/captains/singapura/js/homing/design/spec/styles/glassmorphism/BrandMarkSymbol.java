package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** glassmorphism's implementation of brand-mark_symbol: the base's, by delegation. */
public record BrandMarkSymbol() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseMarks.BrandMark, HouseSaying.Symbol> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseMarks.BrandMark, HouseSaying.Symbol> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.BrandMarkSymbol());
    }
}
