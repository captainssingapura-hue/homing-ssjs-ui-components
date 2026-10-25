package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The grid's cell in the base layer: no look of its own - what it holds draws, its place the split grid's, a layout's;
 * every style's alike.
 */
public record GridCell() implements CastingStyle<BaseScheme, Base, HouseContainers.GridCell, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.GridCell> style() {
        return new StylingRes.CssContent<>();
    }
}
