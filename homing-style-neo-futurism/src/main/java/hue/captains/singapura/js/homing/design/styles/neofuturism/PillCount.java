package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** NeoFuturism's pill count: as its pill on its own, by delegation. */
public record PillCount() implements CastingStyle<BaseScheme, NeoFuturism, HouseText.Pill, HouseSaying.Count> {
    @Override public StylingRes<BaseScheme, HouseText.Pill> style() {
        return new StylingRes.Delegation<>(new Pill());
    }
}
