package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import java.util.List;

/** sketchy's look of control-panel: base's, by delegation. */
public record ControlPanel() implements CastingStylePerRole<BaseScheme, Sketchy, HouseControlPanel.ControlPanel, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseControlPanel.ControlPanel, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ControlPanel());
    }

    @Override public PartsSpec<BaseScheme, Sketchy, HouseControlPanel.ControlPanel> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControlPanel.ControlPanel.INSTANCE, HouseContainers.SliderGroup.INSTANCE, HouseDoing.Adjust.INSTANCE, new SliderGroupAdjust()),
                new PartSpec.Cast<>(HouseControlPanel.ControlPanel.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Toggle.INSTANCE, new PlainButtonToggle()),
                new PartSpec.Cast<>(HouseControlPanel.ControlPanel.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Action.INSTANCE, new PlainButtonAction())));
    }
}
