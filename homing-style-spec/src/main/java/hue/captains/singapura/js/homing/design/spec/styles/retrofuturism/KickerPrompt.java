package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's look of kicker_prompt: as its component on its own, by delegation. */
public record KickerPrompt() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Kicker, HouseSaying.Prompt> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Kicker, HouseSaying.Prompt> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
