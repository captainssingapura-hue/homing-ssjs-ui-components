package hue.captains.singapura.js.homing.ui.monitors;

import hue.captains.singapura.js.homing.ui.controllability.ControlTypeAt;
import hue.captains.singapura.js.homing.ui.controllability.ControllabilityExtension;
import hue.captains.singapura.js.homing.ui.controllability.HouseControllability;

import java.util.List;

/** How the monitors are controlled: asked what they show now. */
public record MonitorsControllability() implements ControllabilityExtension {

    @Override public List<ControlTypeAt> classification() {
        return List.of(new ControlTypeAt(HouseMonitors.FocusMonitor.INSTANCE, HouseControllability.MONITOR),
                       new ControlTypeAt(HouseMonitors.StewardMonitor.INSTANCE, HouseControllability.MONITOR));
    }
}
