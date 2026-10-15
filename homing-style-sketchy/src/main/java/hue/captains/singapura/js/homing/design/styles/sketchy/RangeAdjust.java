package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Sketchy's range adjust: as its range on its own, by delegation. */
public record RangeAdjust() implements CastingStyle<ColorScheme.Base, Sketchy, HouseControls.Range, HouseDoing.Adjust> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Range> style() {
        return new StylingRes.Delegation<>(new Range());
    }
}
