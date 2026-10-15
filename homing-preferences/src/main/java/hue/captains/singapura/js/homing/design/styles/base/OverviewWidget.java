package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/**
 * The overview widget in the base layer: no look of its own - a widget draws only components: its rows and the lines
 * between them draw; every style's alike.
 */
public record OverviewWidget()
        implements CastingStyle<ColorScheme.Base, Base, HousePreferences.OverviewWidget, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HousePreferences.OverviewWidget> style() {
        return new StylingRes.CssContent<>();
    }
}
