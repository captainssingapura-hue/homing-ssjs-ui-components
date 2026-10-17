package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** NeoBrutalism's kicker prompt: as its kicker on its own, by delegation. */
public record KickerPrompt()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Kicker, HouseSaying.Prompt> {
    @Override public StylingRes<BaseScheme, HouseText.Kicker> style() {
        return new StylingRes.Delegation<>(new Kicker());
    }
}
