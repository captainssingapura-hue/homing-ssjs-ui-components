package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Neumorphism's kicker status: as its kicker on its own, by delegation. */
public record KickerStatus()
        implements CastingStyle<BaseScheme, Neumorphism, HouseText.Kicker, HouseSaying.Status> {
    @Override public StylingRes<BaseScheme, HouseText.Kicker> style() {
        return new StylingRes.Delegation<>(new Kicker());
    }
}
