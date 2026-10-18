package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of pane-thumbs: its own implementation, and a spec for each of its slots. */
public record PaneThumbsDeclaration() implements CastingDeclaration<BaseScheme, Base, HousePanes.PaneThumbs, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.PaneThumbs, RoleRoot> own() { return new PaneThumbs(); }

    @Override public PartsSpec<BaseScheme, Base, HousePanes.PaneThumbs> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HousePanes.PaneThumbs.INSTANCE, HouseControls.PaneThumb.INSTANCE, HouseShaping.Cell.INSTANCE)));
    }
}
