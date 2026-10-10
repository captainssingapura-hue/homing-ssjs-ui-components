package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.ui.controllability.ControlTypeAt;
import hue.captains.singapura.js.homing.ui.controllability.ControllabilityExtension;
import hue.captains.singapura.js.homing.ui.controllability.HouseControllability;

import java.util.List;

/**
 * How the split grid's components are controlled, where their branch does not say it: the mirror is
 * a control, but what is done to it is the grid's arranging - a room split, removed, the rooms evened
 * out, the layout reset - which its drawing follows.
 */
public record SplitGridControllability() implements ControllabilityExtension {

    @Override public List<ControlTypeAt> classification() {
        return List.of(new ControlTypeAt(HouseSplitGrid.SplitGridMirror.INSTANCE, HouseControllability.ARRANGEABLE));
    }
}
