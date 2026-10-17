package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** neo-futurism's declaration of edge-strip: its own implementation, and a spec for each of its slots. */
public record EdgeStripDeclaration() implements CastingDeclaration<BaseScheme, NeoFuturism, HouseContainers.EdgeStrip, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, NeoFuturism, HouseContainers.EdgeStrip, RoleRoot> own() { return new EdgeStrip(); }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseContainers.EdgeStrip> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseContainers.EdgeStrip.INSTANCE, HouseControls.Lip.INSTANCE, HouseShaping.Reveal.INSTANCE)));
    }
}
