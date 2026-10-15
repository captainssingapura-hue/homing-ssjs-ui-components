package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;

/**
 * The grid's cell in the base layer: no look of its own - what it holds draws, its place the split grid's; every
 * style's alike.
 */
public record GridCell() implements CastingStyle<ColorScheme.Base, Base, HouseSplitGrid.GridCell, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseSplitGrid.GridCell> style() {
        return new StylingRes.CssContent<>();
    }
}
