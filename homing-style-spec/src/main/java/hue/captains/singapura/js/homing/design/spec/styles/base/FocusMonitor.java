package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMonitors;

/** base's implementation of focus-monitor: CSS of its own. */
public record FocusMonitor() implements CastingStylePerRole<BaseScheme, Base, HouseMonitors.FocusMonitor, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseMonitors.FocusMonitor, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
