package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;

import java.util.List;

/** base's declaration of split-grid-mirror: its own implementation, and a spec for each of its slots. */
public record SplitGridMirrorDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseSplitGrid.SplitGridMirror, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Base, HouseSplitGrid.SplitGridMirror, RoleRoot> own() { return new SplitGridMirror(); }

    @Override public PartsSpec<BaseScheme, Base, HouseSplitGrid.SplitGridMirror> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseSplitGrid.SplitGridMirror.INSTANCE, HouseControls.PaneThumb.INSTANCE, HouseShaping.Cell.INSTANCE)));
    }
}
