package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** editorial's look of drop-mark_landing: base's, by delegation. */
public record DropMarkLanding() implements CastingStylePerRole<BaseScheme, Editorial, HouseMarks.DropMark, HouseSaying.Landing> {
    @Override public StylingRes<BaseScheme, Editorial, HouseMarks.DropMark, HouseSaying.Landing> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DropMarkLanding());
    }
}
