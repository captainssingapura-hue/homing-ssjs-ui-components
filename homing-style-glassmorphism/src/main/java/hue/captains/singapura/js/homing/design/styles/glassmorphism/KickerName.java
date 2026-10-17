package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Glassmorphism's kicker name: as its kicker on its own, by delegation. */
public record KickerName()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseText.Kicker, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, HouseText.Kicker> style() {
        return new StylingRes.Delegation<>(new Kicker());
    }
}
