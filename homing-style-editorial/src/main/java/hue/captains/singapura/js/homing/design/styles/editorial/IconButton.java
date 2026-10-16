package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The icon button in editorial: an icon alone: no frame, in the colour of what holds it, washed under the hand. Whole:
 * every property its look has (D9).
 */
public record IconButton() implements CastingStyle<BaseScheme, Editorial, HouseControls.IconButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.IconButton> style() {
        return new StylingRes.CssContent<>(new BaseColours.Wash(), new BaseColours.Border(), new BaseColours.Ring());
    }
}
