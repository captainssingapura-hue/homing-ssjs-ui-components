package hue.captains.singapura.js.homing.ui.controllability;

/**
 * A branch of the control catalogue, a category of options: the root at {@link L0_ControlBranch
 * level 0}, then {@link L1_ControlBranch} and {@link L2_ControlBranch}, each naming a parent one
 * level up. It is named for what its options do to a component - set it by degree, change its
 * state, show it, view it, arrange it - and it is open below the root: a library adds a category
 * or an option under any branch.
 */
public sealed interface ControlBranch extends ControlNode permits L0_ControlBranch, L1_ControlBranch, L2_ControlBranch {

    /** Its level: 0 for the root, one more than its parent's for every other. */
    int level();
}
