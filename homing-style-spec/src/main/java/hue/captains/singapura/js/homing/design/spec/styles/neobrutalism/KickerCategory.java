package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-brutalism's implementation of kicker_category: as its component standalone, by delegation. */
public record KickerCategory() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseText.Kicker, HouseSaying.Category> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseText.Kicker, HouseSaying.Category> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
