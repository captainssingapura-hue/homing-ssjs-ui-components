package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The primary button in sketchy: the one action a view leads with, in the house's own colour as far as its degree.
 * Whole: every property its look has (D9).
 */
public record PrimaryButton()
        implements CastingStyle<ColorScheme.Base, Sketchy, HouseControls.PrimaryButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.PrimaryButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Title(), new HouseColours.Ring(), new HouseColours.PrimaryGround(),
                new HouseColours.PrimaryInk());
    }
}
