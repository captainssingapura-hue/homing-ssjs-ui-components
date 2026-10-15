package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** NeoBrutalism's lede summary: as its lede on its own, by delegation. */
public record LedeSummary()
        implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseText.Lede, HouseSaying.Summary> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Lede> style() {
        return new StylingRes.Delegation<>(new Lede());
    }
}
