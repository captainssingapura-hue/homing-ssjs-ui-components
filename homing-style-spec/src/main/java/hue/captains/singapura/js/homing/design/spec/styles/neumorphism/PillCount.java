package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's look of pill_count: CSS of its own. */
public record PillCount() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseText.Pill, HouseSaying.Count> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseText.Pill, HouseSaying.Count> style() {
        return new StylingRes.CssContent<>(new BaseColours.WashSoft());
    }
}
