package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** sketchy's declaration of edge-strip: its own implementation, and a spec for each of its slots. */
public record EdgeStripDeclaration() implements CastingDeclaration<BaseScheme, Sketchy, HouseContainers.EdgeStrip, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Sketchy, HouseContainers.EdgeStrip, RoleRoot> own() { return new EdgeStrip(); }

    @Override public PartsSpec<BaseScheme, Sketchy, HouseContainers.EdgeStrip> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseContainers.EdgeStrip.INSTANCE, HouseControls.Lip.INSTANCE, HouseShaping.Reveal.INSTANCE)));
    }
}
