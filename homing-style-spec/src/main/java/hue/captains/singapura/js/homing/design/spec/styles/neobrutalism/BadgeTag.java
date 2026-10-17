package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-brutalism's look of badge_tag: CSS of its own. */
public record BadgeTag() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseText.Badge, HouseSaying.Tag> style() {
        return new StylingRes.CssContent<>(new BaseColours.AccentWash());
    }
}
