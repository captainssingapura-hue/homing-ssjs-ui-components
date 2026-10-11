package hue.captains.singapura.js.homing.ui.floating;


import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.controllability.ControlTypeAt;
import hue.captains.singapura.js.homing.ui.controllability.ControllabilityExtension;
import hue.captains.singapura.js.homing.ui.controllability.HouseControllability;

import java.util.List;

/** How the float layer is controlled: opened over the page, and closed. */
public record FloatingControllability() implements ControllabilityExtension {

    @Override public List<ControlTypeAt> classification() {
        return List.of(new ControlTypeAt(HouseContainers.FloatLayer.INSTANCE, HouseControllability.OVERLAY));
    }
}
