package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;

/**
 * The split grid in the base layer: no look of its own - its cells and its lines draw; its frame is flat; every style's
 * alike.
 */
public record SplitGrid() implements CastingStyle<BaseScheme, Base, HouseSplitGrid.SplitGrid, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseSplitGrid.SplitGrid> style() {
        return new StylingRes.CssContent<>();
    }
}
