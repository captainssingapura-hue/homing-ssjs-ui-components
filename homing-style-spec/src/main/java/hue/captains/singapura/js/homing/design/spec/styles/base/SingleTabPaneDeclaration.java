package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of single-tab-pane: its own implementation, and a spec for each of its slots. */
public record SingleTabPaneDeclaration() implements CastingDeclaration<BaseScheme, Base, HousePanes.SingleTabPane, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.SingleTabPane, RoleRoot> own() { return new SingleTabPane(); }

    @Override public PartsSpec<BaseScheme, Base, HousePanes.SingleTabPane> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HousePanes.SingleTabPane.INSTANCE, HouseContainers.TabStrip.INSTANCE, HouseShaping.Tabs.INSTANCE)));
    }
}
