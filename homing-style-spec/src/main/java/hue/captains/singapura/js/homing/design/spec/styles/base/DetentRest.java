package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** base's look of detent_rest: CSS of its own. */
public record DetentRest() implements CastingStylePerRole<BaseScheme, Base, HouseMarks.Detent, HouseSaying.Rest> {
    @Override public StylingRes<BaseScheme, Base, HouseMarks.Detent, HouseSaying.Rest> style() {
        return new StylingRes.CssContent<>(new BaseColours.Muted());
    }
}
