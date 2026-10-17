package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Glassmorphism's lede summary: as its lede on its own, by delegation. */
public record LedeSummary()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseText.Lede, HouseSaying.Summary> {
    @Override public StylingRes<BaseScheme, HouseText.Lede> style() {
        return new StylingRes.Delegation<>(new Lede());
    }
}
