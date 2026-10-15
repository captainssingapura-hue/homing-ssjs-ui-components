package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Base's caption figure: as its caption on its own, by delegation. */
public record CaptionFigure() implements CastingStyle<ColorScheme.Base, Base, HouseText.Caption, HouseSaying.Figure> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Caption> style() {
        return new StylingRes.Delegation<>(new Caption());
    }
}
