package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;

/** neo-futurism's implementation of control-panel: the base's, by delegation. */
public record ControlPanel() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControlPanel.ControlPanel, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControlPanel.ControlPanel, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ControlPanel());
    }
}
