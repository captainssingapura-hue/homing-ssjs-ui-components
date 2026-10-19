package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;

/** base's implementation of control-panel: CSS of its own - its content apart, the colours it draws with typed here. */
public record ControlPanel() implements CssImplementation<BaseScheme, Base, HouseControlPanel.ControlPanel> {}
