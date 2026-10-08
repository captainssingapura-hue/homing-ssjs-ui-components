package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.js.homing.tree.NodeName;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * A node of the control catalogue - WHAT of a component can be controlled from outside it - levelled
 * as the role catalogue is: a {@link ControlBranch} at its level, a category of options, or a
 * {@link ControlOption} at a leaf. Each is a stateless singleton record. The catalogue files the
 * options and nothing more: how an option is controlled is its own, by the means it implements.
 */
public sealed interface ControlNode extends StatelessFunctionalObject permits ControlBranch, ControlOption {

    /** {@code ZoomIn} gives {@code zoom-in}. */
    default NodeName name() { return NodeName.ofType(getClass(), ""); }
}
