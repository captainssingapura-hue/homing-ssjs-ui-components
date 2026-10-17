package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** base's implementation of summary-view: CSS of its own. */
public record SummaryView() implements CastingStylePerRole<BaseScheme, Base, HouseContainers.SummaryView, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseContainers.SummaryView, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
