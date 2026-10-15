package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** NeoFuturism's caption value: as its caption on its own, by delegation. */
public record CaptionValue()
        implements CastingStyle<ColorScheme.Base, NeoFuturism, HouseText.Caption, HouseSaying.Value> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Caption> style() {
        return new StylingRes.Delegation<>(new Caption());
    }
}
