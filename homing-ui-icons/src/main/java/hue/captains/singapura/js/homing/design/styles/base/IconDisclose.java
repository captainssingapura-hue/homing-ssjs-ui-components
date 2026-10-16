package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Base's icon disclose: as its icon on its own, by delegation. */
public record IconDisclose() implements CastingStyle<BaseScheme, Base, HouseMarks.Icon, HouseSaying.Disclose> {
    @Override public StylingRes<BaseScheme, HouseMarks.Icon> style() {
        return new StylingRes.Delegation<>(new Icon());
    }
}
