package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** base's implementation of boundary-line_splitter: as its component standalone, by delegation. */
public record BoundaryLineSplitter() implements CastingStylePerRole<BaseScheme, Base, HouseMarks.BoundaryLine, HouseDoing.Splitter> {
    @Override public StylingRes<BaseScheme, Base, HouseMarks.BoundaryLine, HouseDoing.Splitter> style() {
        return new StylingRes.RootDelegation<>(new BoundaryLine());
    }
}
