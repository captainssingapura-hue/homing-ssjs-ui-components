package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of trail_path: its own implementation, and a spec for each of its slots. */
public record TrailPathDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseContainers.Trail, HouseDoing.Path> {
    @Override public CastingImplementation<BaseScheme, Base, HouseContainers.Trail, HouseDoing.Path> own() { return new TrailPath(); }

    @Override public PartsSpec<BaseScheme, Base, HouseContainers.Trail> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.Trail.INSTANCE, HouseControls.Crumb.INSTANCE, HouseDoing.Step.INSTANCE, new Target.Impl<>(new CrumbStep())),
                new PartSpec.Standalone<>(HouseContainers.Trail.INSTANCE, HouseMarks.Delimiter.INSTANCE, HouseShaping.Break.INSTANCE)));
    }
}
