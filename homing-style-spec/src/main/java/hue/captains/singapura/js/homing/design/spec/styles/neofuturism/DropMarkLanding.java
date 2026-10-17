package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neo-futurism's implementation of drop-mark_landing: the base's, by delegation. */
public record DropMarkLanding() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseMarks.DropMark, HouseSaying.Landing> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseMarks.DropMark, HouseSaying.Landing> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DropMarkLanding());
    }
}
