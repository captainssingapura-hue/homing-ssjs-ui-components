package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;

import java.util.List;

/** base's declaration of split-grid: its own implementation, and a spec for each of its slots. */
public record SplitGridDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseSplitGrid.SplitGrid, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseSplitGrid.SplitGrid, RoleRoot> own() { return new SplitGrid(); }

    @Override public PartsSpec<BaseScheme, Base, HouseSplitGrid.SplitGrid> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseSplitGrid.SplitGrid.INSTANCE, HouseMarks.BoundaryLine.INSTANCE, HouseDoing.Splitter.INSTANCE, new Target.Impl<>(new BoundaryLineSplitter())),
                new PartSpec.Standalone<>(HouseSplitGrid.SplitGrid.INSTANCE, HouseMarks.BoundaryLine.INSTANCE, HouseShaping.Seam.INSTANCE)));
    }
}
