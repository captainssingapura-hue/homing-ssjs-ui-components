package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;

/** sketchy's implementation of control-panel: the base's, by delegation. */
public record ControlPanel() implements CastingStylePerRole<BaseScheme, Sketchy, HouseControlPanel.ControlPanel, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseControlPanel.ControlPanel, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ControlPanel());
    }
}
