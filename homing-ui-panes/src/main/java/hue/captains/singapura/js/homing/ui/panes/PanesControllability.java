package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ui.controllability.ControlTypeAt;
import hue.captains.singapura.js.homing.ui.controllability.ControllabilityExtension;
import hue.captains.singapura.js.homing.ui.controllability.HouseControllability;

import java.util.List;

/** How the panes' leaves are controlled: the thumbnails arranged, the opener opened over the pane and closed. */
public record PanesControllability() implements ControllabilityExtension {

    @Override public List<ControlTypeAt> classification() {
        return List.of(new ControlTypeAt(HousePanes.TabOpener.INSTANCE, HouseControllability.OVERLAY),
                       new ControlTypeAt(HousePanes.PaneThumbs.INSTANCE, HouseControllability.ARRANGEABLE));
    }
}
