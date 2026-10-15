package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Glassmorphism's pill count: as its pill on its own, by delegation. */
public record PillCount() implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseText.Pill, HouseSaying.Count> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Pill> style() {
        return new StylingRes.Delegation<>(new Pill());
    }
}
