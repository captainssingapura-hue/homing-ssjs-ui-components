package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Neumorphism's lede summary: as its lede on its own, by delegation. */
public record LedeSummary()
        implements CastingStyle<BaseScheme, Neumorphism, HouseText.Lede, HouseSaying.Summary> {
    @Override public StylingRes<BaseScheme, HouseText.Lede> style() {
        return new StylingRes.Delegation<>(new Lede());
    }
}
