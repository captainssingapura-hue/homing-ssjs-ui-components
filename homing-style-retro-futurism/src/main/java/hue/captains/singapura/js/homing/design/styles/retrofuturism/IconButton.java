package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The icon button in retro-futurism: an icon alone: no frame, in the colour of what holds it, washed under the hand.
 * Whole: every property its look has (D9).
 */
public record IconButton()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseControls.IconButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.IconButton> style() {
        return new StylingRes.CssContent<>(new HouseColours.Wash(), new HouseColours.Border(), new HouseColours.Ring());
    }
}
