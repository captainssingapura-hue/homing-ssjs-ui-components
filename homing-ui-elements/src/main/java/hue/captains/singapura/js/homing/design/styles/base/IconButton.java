package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The icon button in the base layer: an icon alone: no frame, in the colour of what holds it, washed under the hand.
 * Whole: every property its look has (D9).
 */
public record IconButton() implements CastingStyle<ColorScheme.Base, Base, HouseControls.IconButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.IconButton> style() {
        return new StylingRes.CssContent<>(new HouseColours.Wash(), new HouseColours.Border(), new HouseColours.Ring());
    }
}
