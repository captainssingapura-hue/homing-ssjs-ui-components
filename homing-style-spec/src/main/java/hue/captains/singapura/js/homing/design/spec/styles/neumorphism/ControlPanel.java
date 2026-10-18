package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;

/** neumorphism's implementation of control-panel: the base's, by delegation. */
public record ControlPanel() implements ParentDelegation<BaseScheme, Base, Neumorphism, HouseControlPanel.ControlPanel, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseControlPanel.ControlPanel, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.ControlPanel(); }
}
