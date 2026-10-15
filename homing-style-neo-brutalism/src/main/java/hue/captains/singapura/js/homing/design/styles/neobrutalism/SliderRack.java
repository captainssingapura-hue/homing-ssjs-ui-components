package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The slider rack in neo-brutalism: a slider group's sliders, stacked or side by side - the room between them, its
 * group's. Whole: every property its look has (D9).
 */
public record SliderRack() implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseRegions.SliderRack, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseRegions.SliderRack> style() {
        return new StylingRes.CssContent<>();
    }
}
