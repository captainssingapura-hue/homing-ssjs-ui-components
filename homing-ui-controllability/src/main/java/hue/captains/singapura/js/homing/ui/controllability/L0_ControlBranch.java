package hue.captains.singapura.js.homing.ui.controllability;

/**
 * The root level of the control catalogue: the one level with no parent, and the one branch nobody
 * adds - it permits the {@link ControlRoot} alone.
 */
public sealed interface L0_ControlBranch extends ControlBranch permits ControlRoot {

    @Override default int level() { return 0; }
}
