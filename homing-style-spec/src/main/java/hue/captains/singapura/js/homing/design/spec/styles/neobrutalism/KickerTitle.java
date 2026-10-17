package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-brutalism's implementation of kicker_title: as its component standalone, by delegation. */
public record KickerTitle() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseText.Kicker, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseText.Kicker, HouseSaying.Title> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
