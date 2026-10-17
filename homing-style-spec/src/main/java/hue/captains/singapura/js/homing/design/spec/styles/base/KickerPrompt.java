package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's look of kicker_prompt: as its component on its own, by delegation. */
public record KickerPrompt() implements CastingStylePerRole<BaseScheme, Base, HouseText.Kicker, HouseSaying.Prompt> {
    @Override public StylingRes<BaseScheme, Base, HouseText.Kicker, HouseSaying.Prompt> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
