package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The preferences button in neumorphism: opens a site's preferences - outlined in the colour of the bar that holds it,
 * its words in it, in the style's face; washed in that colour under the hand, ringed by the focus, faded while it is
 * off. Whole: every property its look has (D9).
 */
public record PreferencesButton()
        implements CastingStyle<ColorScheme.Base, Neumorphism, HouseControls.PreferencesButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.PreferencesButton> style() {
        return new StylingRes.CssContent<>(new HouseColours.Faint(), new HouseColours.Ring());
    }
}
