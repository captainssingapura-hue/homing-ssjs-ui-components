package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import java.util.List;

/** glassmorphism's look of trail: base's, by delegation. */
public record Trail() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseContainers.Trail, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseContainers.Trail, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Trail());
    }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HouseContainers.Trail> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.Trail.INSTANCE, HouseControls.Crumb.INSTANCE, HouseDoing.Step.INSTANCE, new CrumbStep())));
    }
}
