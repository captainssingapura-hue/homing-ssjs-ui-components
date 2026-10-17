package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** retro-futurism's declaration of floating-pane: its own implementation, and a spec for each of its slots. */
public record FloatingPaneDeclaration() implements CastingDeclaration<BaseScheme, RetroFuturism, HouseContainers.FloatingPane, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, RetroFuturism, HouseContainers.FloatingPane, RoleRoot> own() { return new FloatingPane(); }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HouseContainers.FloatingPane> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseContainers.FloatingPane.INSTANCE, HouseContainers.TitleBar.INSTANCE, HouseShaping.Head.INSTANCE)));
    }
}
