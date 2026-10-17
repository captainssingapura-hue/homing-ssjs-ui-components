package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Glassmorphism's caption figure: as its caption on its own, by delegation. */
public record CaptionFigure()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseText.Caption, HouseSaying.Figure> {
    @Override public StylingRes<BaseScheme, HouseText.Caption> style() {
        return new StylingRes.Delegation<>(new Caption());
    }
}
