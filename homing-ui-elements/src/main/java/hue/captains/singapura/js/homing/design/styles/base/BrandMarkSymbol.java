package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Base's brand mark symbol: as its brand mark on its own, by delegation. */
public record BrandMarkSymbol()
        implements CastingStyle<BaseScheme, Base, HouseMarks.BrandMark, HouseSaying.Symbol> {
    @Override public StylingRes<BaseScheme, HouseMarks.BrandMark> style() {
        return new StylingRes.Delegation<>(new BrandMark());
    }
}
