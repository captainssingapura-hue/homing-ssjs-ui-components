package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** neumorphism's declaration of float-layer: its own implementation, and a spec for each of its slots. */
public record FloatLayerDeclaration() implements CastingDeclaration<BaseScheme, Neumorphism, HouseContainers.FloatLayer, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Neumorphism, HouseContainers.FloatLayer, RoleRoot> own() { return new FloatLayer(); }

    @Override public PartsSpec<BaseScheme, Neumorphism, HouseContainers.FloatLayer> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseContainers.FloatLayer.INSTANCE, HouseContainers.FloatingPane.INSTANCE, HouseShaping.Float.INSTANCE)));
    }
}
