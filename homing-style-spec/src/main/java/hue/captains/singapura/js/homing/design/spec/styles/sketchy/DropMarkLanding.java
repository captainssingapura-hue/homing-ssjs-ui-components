package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** sketchy's look of drop-mark_landing: base's, by delegation. */
public record DropMarkLanding() implements CastingStylePerRole<BaseScheme, Sketchy, HouseMarks.DropMark, HouseSaying.Landing> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseMarks.DropMark, HouseSaying.Landing> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DropMarkLanding());
    }
}
