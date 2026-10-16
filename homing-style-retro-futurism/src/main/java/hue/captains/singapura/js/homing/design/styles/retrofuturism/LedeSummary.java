package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** RetroFuturism's lede summary: as its lede on its own, by delegation. */
public record LedeSummary()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseText.Lede, HouseSaying.Summary> {
    @Override public StylingRes<BaseScheme, HouseText.Lede> style() {
        return new StylingRes.Delegation<>(new Lede());
    }
}
