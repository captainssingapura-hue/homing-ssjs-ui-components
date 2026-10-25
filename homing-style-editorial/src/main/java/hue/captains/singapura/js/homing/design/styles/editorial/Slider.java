package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The slider in editorial: the house's value along a scale - its row, and off; its label, its range
 * and its readout are its parts' (23 Oct). Whole: every property its look has (D9).
 */
public record Slider() implements CastingStyle<BaseScheme, Editorial, HouseControls.Slider, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Slider> style() {
        return new StylingRes.CssContent<>();
    }
}
