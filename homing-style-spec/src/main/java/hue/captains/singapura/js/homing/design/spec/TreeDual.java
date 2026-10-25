package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.spec.dual.DualTree;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A Java tree's JS dual, as an instance runs it (RFC 0071): the nodes the registered components' declarations use, and
 * their ancestors - nothing else - each under its name, its parent named, made by the kit's constructor of its kind. One module, at its tree's
 * address.
 *
 * @param tree   the tree it is the dual of
 * @param nodes  its nodes, each after its parent
 * @param inTree how many nodes the tree has in all, the unused with them
 * @param left   the tokens of the nodes left out: used by no registered component, nor above one that is
 */
public record TreeDual(DualTree<?> tree, List<Node> nodes, int inTree, List<String> left) implements ValueObject {

    public TreeDual {
        Objects.requireNonNull(tree, "TreeDual.tree");
        nodes = List.copyOf(nodes);
        left = List.copyOf(left);
        var names = new HashSet<String>();
        for (Node n : nodes) {
            if (!names.add(n.name())) throw new IllegalArgumentException(tree.name() + ": " + n.name() + " named twice");
            for (Companion c : n.companions()) if (!names.add(c.name())) throw new IllegalArgumentException(tree.name() + ": " + c.name() + " named twice");
        }
    }

    /**
     * One node of the dual.
     *
     * @param name    its JS name, the token's words capitalised: unique in the dual, its tree's tokens being unique
     * @param token   its token
     * @param parent  its parent's name; none for a root
     * @param construction how it is made: the kit's constructor of its kind, with its token, its parent and what else it takes
     * @param refers       the nodes of other trees its construction names, which the dual imports
     * @param companions   what the dual declares beside it, each exported under its own name
     */
    public record Node(String name, String token, Optional<String> parent, String construction, List<Object> refers, List<Companion> companions)
            implements ValueObject {
        public Node {
            Objects.requireNonNull(name, "Node.name");
            Objects.requireNonNull(token, "Node.token");
            Objects.requireNonNull(parent, "Node.parent");
            Objects.requireNonNull(construction, "Node.construction");
            refers = List.copyOf(refers);
            companions = List.copyOf(companions);
        }
    }

    /**
     * What a dual declares beside a node, exported under its own name: a component's fixed type (D30).
     *
     * @param name        its name in the module
     * @param declaration its JavaScript
     */
    public record Companion(String name, String declaration) implements ValueObject {
        public Companion {
            Objects.requireNonNull(name, "Companion.name");
            Objects.requireNonNull(declaration, "Companion.declaration");
        }
    }

    /** Whether it has a node, or a node's companion, of the name. */
    public boolean has(String name) {
        return nodes.stream().anyMatch(n -> n.name().equals(name) || n.companions().stream().anyMatch(c -> c.name().equals(name)));
    }
}
