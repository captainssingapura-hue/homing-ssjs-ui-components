package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Base's drop mark landing: as its drop mark on its own, by delegation. */
public record DropMarkLanding()
        implements CastingStyle<BaseScheme, Base, HouseMarks.DropMark, HouseSaying.Landing> {
    @Override public StylingRes<BaseScheme, HouseMarks.DropMark> style() {
        return new StylingRes.Delegation<>(new DropMark());
    }
}
