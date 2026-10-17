package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** base's implementation of brand-mark_symbol: as its component standalone, by delegation. */
public record BrandMarkSymbol() implements CastingStylePerRole<BaseScheme, Base, HouseMarks.BrandMark, HouseSaying.Symbol> {
    @Override public StylingRes<BaseScheme, Base, HouseMarks.BrandMark, HouseSaying.Symbol> style() {
        return new StylingRes.RootDelegation<>(new BrandMark());
    }
}
