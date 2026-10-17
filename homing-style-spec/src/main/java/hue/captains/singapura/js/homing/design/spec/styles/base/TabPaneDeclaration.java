package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of tab-pane: its own implementation, and a spec for each of its slots. */
public record TabPaneDeclaration() implements CastingDeclaration<BaseScheme, Base, HousePanes.TabPane, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Base, HousePanes.TabPane, RoleRoot> own() { return new TabPane(); }

    @Override public PartsSpec<BaseScheme, Base, HousePanes.TabPane> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HousePanes.TabPane.INSTANCE, HouseControls.Tab.INSTANCE, HouseShaping.Chip.INSTANCE)));
    }
}
