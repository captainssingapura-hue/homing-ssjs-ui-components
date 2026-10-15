package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.controlpanel.HouseControlPanel;
import hue.captains.singapura.js.homing.ui.styles.Base;

/**
 * The control panel in the base layer: no look of its own - its switches, its actions and its slider group draw; every
 * style's alike.
 */
public record ControlPanel() implements CastingStyle<ColorScheme.Base, Base, HouseControlPanel.ControlPanel, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControlPanel.ControlPanel> style() {
        return new StylingRes.CssContent<>();
    }
}
