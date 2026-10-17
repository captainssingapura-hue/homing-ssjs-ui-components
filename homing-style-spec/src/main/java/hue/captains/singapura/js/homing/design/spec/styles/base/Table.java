package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** base's implementation of table: CSS of its own. */
public record Table() implements CastingStylePerRole<BaseScheme, Base, HouseRegions.Table, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseRegions.Table, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
