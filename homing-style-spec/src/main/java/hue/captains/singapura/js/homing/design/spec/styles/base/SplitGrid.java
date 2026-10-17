package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;
import java.util.List;

/** base's look of split-grid: CSS of its own. */
public record SplitGrid() implements CastingStylePerRole<BaseScheme, Base, HouseSplitGrid.SplitGrid, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseSplitGrid.SplitGrid, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }

    @Override public PartsSpec<BaseScheme, Base, HouseSplitGrid.SplitGrid> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseSplitGrid.SplitGrid.INSTANCE, HouseMarks.BoundaryLine.INSTANCE, HouseDoing.Splitter.INSTANCE, new BoundaryLineSplitter())));
    }
}
