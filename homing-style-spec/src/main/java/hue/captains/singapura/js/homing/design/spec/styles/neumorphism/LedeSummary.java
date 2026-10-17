package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's look of lede_summary: as its component on its own, by delegation. */
public record LedeSummary() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseText.Lede, HouseSaying.Summary> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseText.Lede, HouseSaying.Summary> style() {
        return new StylingRes.RootDelegation<>(new Lede());
    }
}
