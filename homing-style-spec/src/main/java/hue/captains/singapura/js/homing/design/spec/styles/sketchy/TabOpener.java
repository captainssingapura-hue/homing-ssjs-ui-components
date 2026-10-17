package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** sketchy's implementation of tab-opener: the base's, by delegation. */
public record TabOpener() implements CastingStylePerRole<BaseScheme, Sketchy, HousePanes.TabOpener, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HousePanes.TabOpener, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabOpener());
    }
}
