package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** RetroFuturism's kicker prompt: as its kicker on its own, by delegation. */
public record KickerPrompt()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseText.Kicker, HouseSaying.Prompt> {
    @Override public StylingRes<BaseScheme, HouseText.Kicker> style() {
        return new StylingRes.Delegation<>(new Kicker());
    }
}
