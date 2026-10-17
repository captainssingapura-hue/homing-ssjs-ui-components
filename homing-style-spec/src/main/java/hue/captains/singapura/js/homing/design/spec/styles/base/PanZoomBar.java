package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** base's look of pan-zoom-bar: CSS of its own. */
public record PanZoomBar() implements CastingStylePerRole<BaseScheme, Base, HousePanZoom.PanZoomBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HousePanZoom.PanZoomBar, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }

    @Override public PartsSpec<BaseScheme, Base, HousePanZoom.PanZoomBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePanZoom.PanZoomBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.ZoomOut.INSTANCE, new PlainButtonZoomOut()),
                new PartSpec.Cast<>(HousePanZoom.PanZoomBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.ZoomIn.INSTANCE, new PlainButtonZoomIn()),
                new PartSpec.Cast<>(HousePanZoom.PanZoomBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Fit.INSTANCE, new PlainButtonFit()),
                new PartSpec.Cast<>(HousePanZoom.PanZoomBar.INSTANCE, HouseText.Readout.INSTANCE, HouseSaying.Value.INSTANCE, new ReadoutValue())));
    }
}
