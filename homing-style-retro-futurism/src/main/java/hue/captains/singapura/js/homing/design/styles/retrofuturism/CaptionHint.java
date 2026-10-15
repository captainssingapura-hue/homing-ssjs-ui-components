package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** RetroFuturism's caption hint: as its caption on its own, by delegation. */
public record CaptionHint()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseText.Caption, HouseSaying.Hint> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Caption> style() {
        return new StylingRes.Delegation<>(new Caption());
    }
}
