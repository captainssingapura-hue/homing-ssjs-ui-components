package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * A node's parent, read off its level - one exhaustive switch over the sealed levels, so a level
 * added without its case does not compile. None for the root.
 */
record ControlLevels() implements StatelessFunctionalObject {

    static final ControlLevels INSTANCE = new ControlLevels();

    /** The branch a node of the control catalogue is filed under; none for the root. */
    ControlBranch parentOf(ControlNode n) {
        return switch (n) {
            case ControlOption<?> o    -> o.parent();
            case L0_ControlBranch root -> null;
            case L1_ControlBranch<?> b -> b.parent();
            case L2_ControlBranch<?> b -> b.parent();
        };
    }
}
