package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMonitors;

/**
 * The focus monitor in the base layer: no look of its own - a widget draws only components: its rows and marks draw;
 * every style's alike.
 */
public record FocusMonitor() implements CastingStyle<ColorScheme.Base, Base, HouseMonitors.FocusMonitor, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMonitors.FocusMonitor> style() {
        return new StylingRes.CssContent<>();
    }
}
