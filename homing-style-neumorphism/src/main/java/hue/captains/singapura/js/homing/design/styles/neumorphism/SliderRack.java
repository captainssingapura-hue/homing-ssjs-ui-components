package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The slider rack in neumorphism: a slider group's sliders, stacked or side by side - the room between them, its
 * group's. Whole: every property its look has (D9).
 */
public record SliderRack() implements CastingStyle<BaseScheme, Neumorphism, HouseRegions.SliderRack, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseRegions.SliderRack> style() {
        return new StylingRes.CssContent<>();
    }
}
