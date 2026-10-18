package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** glassmorphism's declaration of pane-thumbs_where: its own implementation, and a spec for each of its slots. */
public record PaneThumbsWhereDeclaration() implements CastingDeclaration<BaseScheme, Glassmorphism, HousePanes.PaneThumbs, HouseDoing.Where> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HousePanes.PaneThumbs, HouseDoing.Where> own() { return new PaneThumbsWhere(); }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HousePanes.PaneThumbs> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HousePanes.PaneThumbs.INSTANCE, HouseControls.PaneThumb.INSTANCE, HouseShaping.Cell.INSTANCE)));
    }
}
