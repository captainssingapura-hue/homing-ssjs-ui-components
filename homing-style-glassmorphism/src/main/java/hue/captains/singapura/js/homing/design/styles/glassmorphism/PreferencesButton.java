package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The preferences button in glassmorphism: opens a site's preferences - outlined in the colour of the bar that holds
 * it, its words in it, in the style's face; washed in that colour under the hand, ringed by the focus, faded while it
 * is off. Whole: every property its look has (D9).
 */
public record PreferencesButton()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseControls.PreferencesButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.PreferencesButton> style() {
        return new StylingRes.CssContent<>(new BaseColours.Faint(), new BaseColours.Ring());
    }
}
