package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** sketchy's declaration of pan-zoom-bar: its own implementation, and a spec for each of its slots. */
public record PanZoomBarDeclaration() implements CastingDeclaration<BaseScheme, Sketchy, HousePanZoom.PanZoomBar, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Sketchy, HousePanZoom.PanZoomBar, RoleRoot> own() { return new PanZoomBar(); }

    @Override public PartsSpec<BaseScheme, Sketchy, HousePanZoom.PanZoomBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePanZoom.PanZoomBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.ZoomOut.INSTANCE, new Target.Impl<>(new PlainButtonZoomOut())),
                new PartSpec.SameRole<>(HousePanZoom.PanZoomBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.ZoomIn.INSTANCE, new Target.Impl<>(new PlainButtonZoomIn())),
                new PartSpec.SameRole<>(HousePanZoom.PanZoomBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Fit.INSTANCE, new Target.Impl<>(new PlainButtonFit())),
                new PartSpec.SameRole<>(HousePanZoom.PanZoomBar.INSTANCE, HouseText.Readout.INSTANCE, HouseSaying.Value.INSTANCE, new Target.Impl<>(new ReadoutValue()))));
    }
}
