package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.spec.dual.DualTree;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * A Java tree's JS dual, as an instance runs it (RFC 0071): the nodes the registered components' declarations use, and
 * their ancestors - nothing else - each under its name, its parent named, what it carries. One module, at its tree's
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
    }

    /**
     * One node of the dual.
     *
     * @param name    its JS name, the token's words capitalised: unique in the dual, its tree's tokens being unique
     * @param token   its token
     * @param parent  its parent's name; none for a root
     * @param carries what it carries for the page, each a JS literal, by its key
     */
    public record Node(String name, String token, Optional<String> parent, Map<String, String> carries) implements ValueObject {
        public Node {
            Objects.requireNonNull(name, "Node.name");
            Objects.requireNonNull(token, "Node.token");
            Objects.requireNonNull(parent, "Node.parent");
            carries = Collections.unmodifiableMap(new LinkedHashMap<>(carries));
        }
    }

    /** Whether it has a node of the name. */
    public boolean has(String name) { return nodes.stream().anyMatch(n -> n.name().equals(name)); }
}
