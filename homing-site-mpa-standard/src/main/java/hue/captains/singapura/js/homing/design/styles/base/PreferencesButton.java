package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The preferences button in the base layer: opens a site's preferences - outlined in the colour of the bar that holds
 * it, its words in it, in the face it is opened on; washed in that colour under the hand, ringed by the focus, faded
 * while it is off. Whole: every property its look has (D9).
 */
public record PreferencesButton()
        implements CastingStyle<BaseScheme, Base, HouseControls.PreferencesButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.PreferencesButton> style() {
        return new StylingRes.CssContent<>(new HouseColours.Faint(), new HouseColours.Ring());
    }
}
