package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;
import java.util.Set;

/**
 * One Java tree read the one way every tree is (RFC 0071): its roots, each node's children, each node's token - unique in
 * its tree, the reader holds it so - what a node carries for the page, and the leaves the registered components'
 * declarations use. From it one generator makes the tree's JS dual ({@code ReadDual}, {@code DualScript}), and one pure
 * function says, for any node, which dual it is in and under which name ({@code DualRef}). Each reading is a class of its
 * own, its canonical name the dual's address.
 *
 * @param <N> the tree's node type
 */
public sealed interface DualTree<N> extends StatelessFunctionalObject permits Components, Roles, States, Events, Extents, ElementPaths, Styles {

    /** The tree's name, as the page reads it: {@code components}. */
    String name();

    /** What an imported node's name begins with, so two trees' nodes never share a name in one module: {@code Component}. */
    String prefix();

    /** Its roots: its root, or every value of a flat one. */
    List<N> roots(Registration registration);

    /** A node's children, branches first; none for a leaf. */
    List<N> children(Registration registration, N node);

    /** A node's token, unique in its tree. */
    String token(N node);

    /** A node's JS name in its dual: its token's words capitalised, joined - {@code plain-button} is {@code PlainButton}. */
    default String name(N node) { return hue.captains.singapura.js.homing.design.spec.JsLiterals.INSTANCE.name(token(node)); }

    /** The nodes of other trees a node's construction names, which its dual imports from theirs; none by default. */
    default List<Object> refers(Registration registration, N node) { return List.of(); }

    /**
     * A node's construction in its dual: the kit's constructor of its kind, called with its token, its parent and what else
     * the kind takes - {@code new Component("plain-button", Buttons, false)}.
     *
     * @param parent the parent's name in the dual, {@code null} for a root
     * @param above  the node's ancestors, the root first
     */
    String construct(Registration registration, N node, String parent, List<N> above);

    /** The kit's classes its dual is made with ({@link DualKit}). */
    List<String> kit();

    /** The leaves the registered components' declarations use: what the dual keeps, with their ancestors, and nothing else. */
    Set<N> used(Registration registration);

    /** The dual's address: its class's canonical name, as every module's is. */
    default String address() { return "/module?class=" + getClass().getCanonicalName(); }
}
