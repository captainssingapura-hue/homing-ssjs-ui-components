package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** sketchy's implementation of trail: the base's, by delegation. */
public record Trail() implements CastingStylePerRole<BaseScheme, Sketchy, HouseContainers.Trail, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseContainers.Trail, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Trail());
    }
}
