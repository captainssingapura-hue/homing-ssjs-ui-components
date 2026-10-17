package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import java.util.List;

/** sketchy's look of trail_path: base's, by delegation. */
public record TrailPath() implements CastingStylePerRole<BaseScheme, Sketchy, HouseContainers.Trail, HouseDoing.Path> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseContainers.Trail, HouseDoing.Path> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TrailPath());
    }

    @Override public PartsSpec<BaseScheme, Sketchy, HouseContainers.Trail> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.Trail.INSTANCE, HouseControls.Crumb.INSTANCE, HouseDoing.Step.INSTANCE, new CrumbStep())));
    }
}
