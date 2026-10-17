package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** glassmorphism's declaration of panel: its own implementation, and a spec for each of its slots. */
public record PanelDeclaration() implements CastingDeclaration<BaseScheme, Glassmorphism, HouseContainers.Panel, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Glassmorphism, HouseContainers.Panel, RoleRoot> own() { return new Panel(); }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HouseContainers.Panel> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseContainers.Panel.INSTANCE, HouseContainers.PanelBar.INSTANCE, HouseShaping.Head.INSTANCE),
                new PartSpec.Standalone<>(HouseContainers.Panel.INSTANCE, HouseRegions.Section.INSTANCE, HouseShaping.Body.INSTANCE)));
    }
}
