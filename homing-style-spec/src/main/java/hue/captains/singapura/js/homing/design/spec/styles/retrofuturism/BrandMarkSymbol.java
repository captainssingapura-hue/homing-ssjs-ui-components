package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** retro-futurism's look of brand-mark_symbol: base's, by delegation. */
public record BrandMarkSymbol() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseMarks.BrandMark, HouseSaying.Symbol> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseMarks.BrandMark, HouseSaying.Symbol> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.BrandMarkSymbol());
    }
}
