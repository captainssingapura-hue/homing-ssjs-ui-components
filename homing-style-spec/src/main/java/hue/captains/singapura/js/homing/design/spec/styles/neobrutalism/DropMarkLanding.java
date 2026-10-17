package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neo-brutalism's implementation of drop-mark_landing: the base's, by delegation. */
public record DropMarkLanding() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseMarks.DropMark, HouseSaying.Landing> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseMarks.DropMark, HouseSaying.Landing> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DropMarkLanding());
    }
}
