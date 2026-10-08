package hue.captains.singapura.js.homing.ui.controllability;

/** The root of the control catalogue: the one node at level 0, which nobody adds. */
public record ControlRoot() implements L0_ControlBranch {

    public static final ControlRoot INSTANCE = new ControlRoot();
}
