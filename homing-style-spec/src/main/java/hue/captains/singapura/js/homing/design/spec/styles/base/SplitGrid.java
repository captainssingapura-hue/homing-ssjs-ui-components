package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;

/** base's implementation of split-grid: CSS of its own - its content apart, the colours it draws with typed here. */
public record SplitGrid() implements CssImplementation<BaseScheme, Base, HouseSplitGrid.SplitGrid> {}
