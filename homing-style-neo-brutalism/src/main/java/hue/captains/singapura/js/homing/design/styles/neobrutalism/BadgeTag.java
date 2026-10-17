package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** NeoBrutalism's badge tag: as its badge on its own, by delegation. */
public record BadgeTag() implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<BaseScheme, HouseText.Badge> style() {
        return new StylingRes.Delegation<>(new Badge());
    }
}
