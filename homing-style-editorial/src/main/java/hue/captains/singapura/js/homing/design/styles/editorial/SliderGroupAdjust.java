package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Editorial's slider group adjust: as its slider group on its own, by delegation. */
public record SliderGroupAdjust()
        implements CastingStyle<BaseScheme, Editorial, HouseContainers.SliderGroup, HouseDoing.Adjust> {
    @Override public StylingRes<BaseScheme, HouseContainers.SliderGroup> style() {
        return new StylingRes.Delegation<>(new SliderGroup());
    }
}
