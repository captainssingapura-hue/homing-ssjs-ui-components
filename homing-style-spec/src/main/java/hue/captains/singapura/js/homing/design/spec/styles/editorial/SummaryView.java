package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** editorial's implementation of summary-view: the base's, by delegation. */
public record SummaryView() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.SummaryView, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseContainers.SummaryView, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SummaryView());
    }
}
