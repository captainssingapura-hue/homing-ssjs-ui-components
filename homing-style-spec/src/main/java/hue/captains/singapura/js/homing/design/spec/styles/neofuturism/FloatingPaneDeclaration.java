package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** neo-futurism's declaration of floating-pane: its own implementation, and a spec for each of its slots. */
public record FloatingPaneDeclaration() implements CastingDeclaration<BaseScheme, NeoFuturism, HouseContainers.FloatingPane, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseContainers.FloatingPane, RoleRoot> own() { return new FloatingPane(); }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseContainers.FloatingPane> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseContainers.FloatingPane.INSTANCE, HouseContainers.TitleBar.INSTANCE, HouseShaping.Head.INSTANCE)));
    }
}
