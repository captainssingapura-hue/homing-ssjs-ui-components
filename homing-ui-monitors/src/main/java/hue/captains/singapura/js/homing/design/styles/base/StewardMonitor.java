package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMonitors;

/**
 * The steward monitor in the base layer: no look of its own - a widget draws only components: its lamp and its rows
 * draw; every style's alike.
 */
public record StewardMonitor() implements CastingStyle<BaseScheme, Base, HouseMonitors.StewardMonitor, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMonitors.StewardMonitor> style() {
        return new StylingRes.CssContent<>();
    }
}
