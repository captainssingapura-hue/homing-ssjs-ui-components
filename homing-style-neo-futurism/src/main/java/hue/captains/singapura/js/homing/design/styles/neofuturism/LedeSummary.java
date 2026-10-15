package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** NeoFuturism's lede summary: as its lede on its own, by delegation. */
public record LedeSummary()
        implements CastingStyle<ColorScheme.Base, NeoFuturism, HouseText.Lede, HouseSaying.Summary> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Lede> style() {
        return new StylingRes.Delegation<>(new Lede());
    }
}
