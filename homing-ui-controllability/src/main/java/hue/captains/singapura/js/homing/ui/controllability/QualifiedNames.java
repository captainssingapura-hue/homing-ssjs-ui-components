package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/** A node's name, as its class and any it is nested in say it. */
public record QualifiedNames() implements StatelessFunctionalObject {

    public static final QualifiedNames INSTANCE = new QualifiedNames();

    /** The node's class's name, its package left off. */
    /** A node as a problem says it: its type without the package, nesting kept. */
    public String of(Object node) {
        String n = node.getClass().getName();
        return n.substring(n.lastIndexOf('.') + 1);
    }
}
