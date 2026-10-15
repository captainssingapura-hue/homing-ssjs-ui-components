package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.preferences.HousePreferences;
import hue.captains.singapura.js.homing.ui.styles.Base;

/**
 * The scale widget in the base layer: no look of its own - a widget draws only components: its range and its readout
 * draw; every style's alike.
 */
public record ScaleWidget() implements CastingStyle<ColorScheme.Base, Base, HousePreferences.ScaleWidget, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HousePreferences.ScaleWidget> style() {
        return new StylingRes.CssContent<>();
    }
}
