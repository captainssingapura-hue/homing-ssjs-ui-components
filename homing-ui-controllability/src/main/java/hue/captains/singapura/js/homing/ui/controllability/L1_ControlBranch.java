package hue.captains.singapura.js.homing.ui.controllability;

/**
 * A branch at level 1 of the control catalogue - a category of options: its parent is the root.
 *
 * @param <P> the branch it is filed under, one level up
 */
public non-sealed interface L1_ControlBranch<P extends L0_ControlBranch> extends ControlBranch {

    /** The branch it is filed under, one level up. */
    P parent();

    @Override default int level() { return 1; }
}
