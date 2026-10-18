package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** neo-futurism's declaration of panel: its own implementation, and a spec for each of its slots. */
public record PanelDeclaration() implements CastingDeclaration<BaseScheme, NeoFuturism, HouseContainers.Panel, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseContainers.Panel, RoleRoot> own() { return new Panel(); }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseContainers.Panel> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseContainers.Panel.INSTANCE, HouseContainers.PanelBar.INSTANCE, HouseShaping.Head.INSTANCE),
                new PartSpec.Standalone<>(HouseContainers.Panel.INSTANCE, HouseRegions.Section.INSTANCE, HouseShaping.Body.INSTANCE)));
    }
}
