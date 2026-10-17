package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** sketchy's implementation of fill_level: as its component standalone, by delegation. */
public record FillLevel() implements CastingStylePerRole<BaseScheme, Sketchy, HouseMarks.Fill, HouseSaying.Level> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseMarks.Fill, HouseSaying.Level> style() {
        return new StylingRes.RootDelegation<>(new Fill());
    }
}
