package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.preferences.HousePreferences;
import hue.captains.singapura.js.homing.ui.styles.Base;

/**
 * The setting row in the base layer: one setting of a group, listed with its value - a list row's measure, still; the
 * line between rows a separator its list places. Every style's alike: the preferences' own leaf. Whole: every property
 * its look has (D9).
 */
public record SettingRow() implements CastingStyle<ColorScheme.Base, Base, HousePreferences.SettingRow, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HousePreferences.SettingRow> style() {
        return new StylingRes.CssContent<>();
    }
}
