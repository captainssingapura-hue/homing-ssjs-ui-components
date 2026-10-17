package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Neumorphism's wordmark name: as its wordmark on its own, by delegation. */
public record WordmarkName()
        implements CastingStyle<BaseScheme, Neumorphism, HouseText.Wordmark, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, HouseText.Wordmark> style() {
        return new StylingRes.Delegation<>(new Wordmark());
    }
}
