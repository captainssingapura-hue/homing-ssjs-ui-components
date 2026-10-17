package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** neo-futurism's look of pan-zoom-bar: base's, by delegation. */
public record PanZoomBar() implements CastingStylePerRole<BaseScheme, NeoFuturism, HousePanZoom.PanZoomBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HousePanZoom.PanZoomBar, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PanZoomBar());
    }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HousePanZoom.PanZoomBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePanZoom.PanZoomBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.ZoomOut.INSTANCE, new PlainButtonZoomOut()),
                new PartSpec.Cast<>(HousePanZoom.PanZoomBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.ZoomIn.INSTANCE, new PlainButtonZoomIn()),
                new PartSpec.Cast<>(HousePanZoom.PanZoomBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Fit.INSTANCE, new PlainButtonFit()),
                new PartSpec.Cast<>(HousePanZoom.PanZoomBar.INSTANCE, HouseText.Readout.INSTANCE, HouseSaying.Value.INSTANCE, new ReadoutValue())));
    }
}
