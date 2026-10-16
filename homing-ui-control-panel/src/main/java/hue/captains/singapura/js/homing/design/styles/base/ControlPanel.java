package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;

/**
 * The control panel in the base layer: no look of its own - its switches, its actions and its slider group draw; every
 * style's alike.
 */
public record ControlPanel() implements CastingStyle<BaseScheme, Base, HouseControlPanel.ControlPanel, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControlPanel.ControlPanel> style() {
        return new StylingRes.CssContent<>();
    }
}
