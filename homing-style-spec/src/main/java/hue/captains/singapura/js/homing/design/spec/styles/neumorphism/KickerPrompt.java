package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's implementation of kicker_prompt: as its component standalone, by delegation. */
public record KickerPrompt() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseText.Kicker, HouseSaying.Prompt> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseText.Kicker, HouseSaying.Prompt> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
