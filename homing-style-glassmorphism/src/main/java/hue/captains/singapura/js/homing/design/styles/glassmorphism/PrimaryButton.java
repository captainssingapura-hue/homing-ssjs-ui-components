package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The primary button in glassmorphism: the one action a view leads with, in the house's own colour as far as its
 * degree. Whole: every property its look has (D9).
 */
public record PrimaryButton()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseControls.PrimaryButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.PrimaryButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Title(), new HouseColours.Ring(), new HouseColours.ShadeSoft(),
                new HouseColours.ShadeFirm(), new HouseColours.Sheen(), new HouseColours.PrimaryGround(),
                new HouseColours.PrimaryInk());
    }
}
