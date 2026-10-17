package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** The table in the base layer: no look of its own - what it holds draws; every style's alike. */
public record Table() implements CastingStyle<BaseScheme, Base, HouseRegions.Table, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseRegions.Table> style() {
        return new StylingRes.CssContent<>();
    }
}
