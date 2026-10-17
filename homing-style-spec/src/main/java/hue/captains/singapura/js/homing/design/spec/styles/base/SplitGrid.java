package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;

/** base's implementation of split-grid: CSS of its own. */
public record SplitGrid() implements CastingStylePerRole<BaseScheme, Base, HouseSplitGrid.SplitGrid, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseSplitGrid.SplitGrid, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
