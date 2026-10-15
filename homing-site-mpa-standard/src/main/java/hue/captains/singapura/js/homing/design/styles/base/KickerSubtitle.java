package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Base's kicker subtitle: as its kicker on its own, by delegation. */
public record KickerSubtitle() implements CastingStyle<ColorScheme.Base, Base, HouseText.Kicker, HouseSaying.Subtitle> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Kicker> style() {
        return new StylingRes.Delegation<>(new Kicker());
    }
}
