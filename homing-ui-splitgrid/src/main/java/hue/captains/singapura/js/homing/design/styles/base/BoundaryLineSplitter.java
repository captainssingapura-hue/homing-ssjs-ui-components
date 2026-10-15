package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** Base's boundary line splitter: as its boundary line on its own, by delegation. */
public record BoundaryLineSplitter()
        implements CastingStyle<ColorScheme.Base, Base, HouseMarks.BoundaryLine, HouseDoing.Splitter> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.BoundaryLine> style() {
        return new StylingRes.Delegation<>(new BoundaryLine());
    }
}
