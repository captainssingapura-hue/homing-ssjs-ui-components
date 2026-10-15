package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.ui.controllability.ControlTypeAt;
import hue.captains.singapura.js.homing.ui.controllability.ControllabilityExtension;
import hue.captains.singapura.js.homing.ui.controllability.HouseControllability;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

import java.util.List;

/** How the picture is controlled: zoomed in and out, and fitted. */
public record PanZoomControllability() implements ControllabilityExtension {

    @Override public List<ControlTypeAt> classification() {
        return List.of(new ControlTypeAt(HousePanZoom.SvgPanZoom.INSTANCE, HouseControllability.ZOOMABLE));
    }
}
