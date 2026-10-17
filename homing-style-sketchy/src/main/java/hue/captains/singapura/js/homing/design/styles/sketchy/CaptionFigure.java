package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Sketchy's caption figure: as its caption on its own, by delegation. */
public record CaptionFigure()
        implements CastingStyle<BaseScheme, Sketchy, HouseText.Caption, HouseSaying.Figure> {
    @Override public StylingRes<BaseScheme, HouseText.Caption> style() {
        return new StylingRes.Delegation<>(new Caption());
    }
}
