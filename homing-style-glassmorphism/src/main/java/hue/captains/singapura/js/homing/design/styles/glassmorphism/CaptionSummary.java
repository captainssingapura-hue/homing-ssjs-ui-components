package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Glassmorphism's caption summary: as its caption on its own, by delegation. */
public record CaptionSummary()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseText.Caption, HouseSaying.Summary> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Caption> style() {
        return new StylingRes.Delegation<>(new Caption());
    }
}
