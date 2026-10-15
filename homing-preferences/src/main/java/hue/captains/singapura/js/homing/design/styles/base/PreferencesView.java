package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.preferences.HousePreferences;
import hue.captains.singapura.js.homing.ui.styles.Base;

/** The preferences view in the base layer: no look of its own - the widgets it lays out draw; every style's alike. */
public record PreferencesView()
        implements CastingStyle<ColorScheme.Base, Base, HousePreferences.PreferencesView, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HousePreferences.PreferencesView> style() {
        return new StylingRes.CssContent<>();
    }
}
