package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The range in neo-brutalism: a value along a scale, the browser's own, in the accent. Whole: every property its look
 * has (D9).
 */
public record Range() implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseControls.Range, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Range> style() {
        return new StylingRes.CssContent<>(new HouseColours.Accent(), new HouseColours.Ring());
    }
}
