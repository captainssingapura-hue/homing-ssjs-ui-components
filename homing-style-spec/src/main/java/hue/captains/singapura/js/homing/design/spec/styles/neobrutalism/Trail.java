package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neo-brutalism's implementation of trail: the base's, by delegation. */
public record Trail() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.Trail, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseContainers.Trail, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Trail());
    }
}
