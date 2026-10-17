package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Editorial's range adjust: as its range on its own, by delegation. */
public record RangeAdjust()
        implements CastingStyle<BaseScheme, Editorial, HouseControls.Range, HouseDoing.Adjust> {
    @Override public StylingRes<BaseScheme, HouseControls.Range> style() {
        return new StylingRes.Delegation<>(new Range());
    }
}
