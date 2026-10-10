package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.ui.controllability.ControlTypeAt;
import hue.captains.singapura.js.homing.ui.controllability.ControllabilityExtension;
import hue.captains.singapura.js.homing.ui.controllability.HouseControllability;

import java.util.List;

/** How the menu steward is controlled: asked what it shows now. */
public record MenusControllability() implements ControllabilityExtension {

    @Override public List<ControlTypeAt> classification() {
        return List.of(new ControlTypeAt(HouseMenus.ContextMenuSteward.INSTANCE, HouseControllability.MONITOR));
    }
}
