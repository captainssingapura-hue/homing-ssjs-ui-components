package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Base's lede summary: as its lede on its own, by delegation. */
public record LedeSummary() implements CastingStyle<ColorScheme.Base, Base, HouseText.Lede, HouseSaying.Summary> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Lede> style() {
        return new StylingRes.Delegation<>(new Lede());
    }
}
