package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's look of kicker_status: CSS of its own. */
public record KickerStatus() implements CastingStylePerRole<BaseScheme, Base, HouseText.Kicker, HouseSaying.Status> {
    @Override public StylingRes<BaseScheme, Base, HouseText.Kicker, HouseSaying.Status> style() {
        return new StylingRes.CssContent<>(new BaseColours.KickerInk());
    }
}
