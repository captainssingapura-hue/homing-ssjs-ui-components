package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Base's caption value: as its caption on its own, by delegation. */
public record CaptionValue() implements CastingStyle<BaseScheme, Base, HouseText.Caption, HouseSaying.Value> {
    @Override public StylingRes<BaseScheme, HouseText.Caption> style() {
        return new StylingRes.Delegation<>(new Caption());
    }
}
