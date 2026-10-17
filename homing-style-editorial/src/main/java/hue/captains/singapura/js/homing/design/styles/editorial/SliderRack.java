package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The slider rack in editorial: a slider group's sliders, stacked or side by side - the room between them, its group's.
 * Whole: every property its look has (D9).
 */
public record SliderRack() implements CastingStyle<BaseScheme, Editorial, HouseRegions.SliderRack, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseRegions.SliderRack> style() {
        return new StylingRes.CssContent<>();
    }
}
