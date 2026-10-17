package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The icon button in sketchy: an icon alone: no frame, in the colour of what holds it, washed under the hand. Whole:
 * every property its look has (D9).
 */
public record IconButton() implements CastingStyle<BaseScheme, Sketchy, HouseControls.IconButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.IconButton> style() {
        return new StylingRes.CssContent<>(new BaseColours.Wash(), new BaseColours.Border(), new BaseColours.Ring());
    }
}
