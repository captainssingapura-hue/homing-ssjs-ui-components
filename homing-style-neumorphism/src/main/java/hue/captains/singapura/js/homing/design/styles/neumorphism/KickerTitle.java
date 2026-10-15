package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Neumorphism's kicker title: as its kicker on its own, by delegation. */
public record KickerTitle()
        implements CastingStyle<ColorScheme.Base, Neumorphism, HouseText.Kicker, HouseSaying.Title> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Kicker> style() {
        return new StylingRes.Delegation<>(new Kicker());
    }
}
