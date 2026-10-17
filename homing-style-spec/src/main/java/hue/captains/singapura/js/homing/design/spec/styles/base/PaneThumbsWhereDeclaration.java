package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of pane-thumbs_where: its own implementation, and a spec for each of its slots. */
public record PaneThumbsWhereDeclaration() implements CastingDeclaration<BaseScheme, Base, HousePanes.PaneThumbs, HouseDoing.Where> {
    @Override public CastingStylePerRole<BaseScheme, Base, HousePanes.PaneThumbs, HouseDoing.Where> own() { return new PaneThumbsWhere(); }

    @Override public PartsSpec<BaseScheme, Base, HousePanes.PaneThumbs> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HousePanes.PaneThumbs.INSTANCE, HouseControls.PaneThumb.INSTANCE, HouseShaping.Cell.INSTANCE)));
    }
}
