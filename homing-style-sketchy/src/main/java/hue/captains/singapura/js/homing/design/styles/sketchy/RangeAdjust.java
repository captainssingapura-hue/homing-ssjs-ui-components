package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Sketchy's range adjust: as its range on its own, by delegation - what adjusts a slider (23 Oct). */
public record RangeAdjust()
        implements CastingStyle<BaseScheme, Sketchy, HouseControls.Range, HouseDoing.Adjust> {
    @Override public StylingRes<BaseScheme, HouseControls.Range> style() {
        return new StylingRes.Delegation<>(new Range());
    }
}
