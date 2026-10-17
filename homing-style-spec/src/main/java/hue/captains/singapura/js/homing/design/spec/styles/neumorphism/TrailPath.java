package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import java.util.List;

/** neumorphism's look of trail_path: base's, by delegation. */
public record TrailPath() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseContainers.Trail, HouseDoing.Path> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseContainers.Trail, HouseDoing.Path> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TrailPath());
    }

    @Override public PartsSpec<BaseScheme, Neumorphism, HouseContainers.Trail> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.Trail.INSTANCE, HouseControls.Crumb.INSTANCE, HouseDoing.Step.INSTANCE, new CrumbStep())));
    }
}
