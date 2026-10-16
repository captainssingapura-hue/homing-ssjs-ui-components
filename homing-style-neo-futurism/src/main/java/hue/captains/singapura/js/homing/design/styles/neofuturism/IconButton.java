package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The icon button in neo-futurism: an icon alone: no frame, in the colour of what holds it, washed under the hand.
 * Whole: every property its look has (D9).
 */
public record IconButton() implements CastingStyle<BaseScheme, NeoFuturism, HouseControls.IconButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.IconButton> style() {
        return new StylingRes.CssContent<>(new HouseColours.Wash(), new HouseColours.Border(), new HouseColours.Ring());
    }
}
