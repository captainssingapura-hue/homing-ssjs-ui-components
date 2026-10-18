package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

import java.util.List;

/** neo-brutalism's declaration of control-panel: its own implementation, and a spec for each of its slots. */
public record ControlPanelDeclaration() implements CastingDeclaration<BaseScheme, NeoBrutalism, HouseControlPanel.ControlPanel, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, NeoBrutalism, HouseControlPanel.ControlPanel, RoleRoot> own() { return new ControlPanel(); }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseControlPanel.ControlPanel> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseControlPanel.ControlPanel.INSTANCE, HouseContainers.SliderGroup.INSTANCE, HouseDoing.Adjust.INSTANCE, new Target.Declared<>(new SliderGroupAdjustDeclaration())),
                new PartSpec.SameRole<>(HouseControlPanel.ControlPanel.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Toggle.INSTANCE, new Target.Impl<>(new PlainButtonToggle())),
                new PartSpec.SameRole<>(HouseControlPanel.ControlPanel.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Action.INSTANCE, new Target.Impl<>(new PlainButtonAction()))));
    }
}
