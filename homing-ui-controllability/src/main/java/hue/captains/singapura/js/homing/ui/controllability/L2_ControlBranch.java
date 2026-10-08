package hue.captains.singapura.js.homing.ui.controllability;

/**
 * A branch at level 2 of the control catalogue - a category within a category: its parent is at
 * level 1.
 *
 * @param <P> the branch it is filed under, one level up
 */
public non-sealed interface L2_ControlBranch<P extends L1_ControlBranch<?>> extends ControlBranch {

    /** The branch it is filed under, one level up. */
    P parent();

    @Override default int level() { return 2; }
}
