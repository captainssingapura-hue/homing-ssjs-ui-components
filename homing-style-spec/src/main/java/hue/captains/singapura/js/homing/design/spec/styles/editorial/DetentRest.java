package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** editorial's implementation of detent_rest: as its component standalone, by delegation. */
public record DetentRest() implements CastingStylePerRole<BaseScheme, Editorial, HouseMarks.Detent, HouseSaying.Rest> {
    @Override public StylingRes<BaseScheme, Editorial, HouseMarks.Detent, HouseSaying.Rest> style() {
        return new StylingRes.RootDelegation<>(new Detent());
    }
}
