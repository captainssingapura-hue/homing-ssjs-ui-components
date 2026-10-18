package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of float-layer: its own implementation, and a spec for each of its slots. */
public record FloatLayerDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseContainers.FloatLayer, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseContainers.FloatLayer, RoleRoot> own() { return new FloatLayer(); }

    @Override public PartsSpec<BaseScheme, Base, HouseContainers.FloatLayer> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseContainers.FloatLayer.INSTANCE, HouseContainers.FloatingPane.INSTANCE, HouseShaping.Float.INSTANCE)));
    }
}
