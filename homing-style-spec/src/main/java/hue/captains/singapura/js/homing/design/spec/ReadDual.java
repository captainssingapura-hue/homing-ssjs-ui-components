package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.spec.dual.Components;
import hue.captains.singapura.js.homing.design.spec.dual.DualTree;
import hue.captains.singapura.js.homing.design.spec.dual.Events;
import hue.captains.singapura.js.homing.design.spec.dual.Extents;
import hue.captains.singapura.js.homing.design.spec.dual.Handles;
import hue.captains.singapura.js.homing.design.spec.dual.Registration;
import hue.captains.singapura.js.homing.design.spec.dual.Roles;
import hue.captains.singapura.js.homing.design.spec.dual.States;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The one way a Java tree becomes its JS dual (RFC 0071): walked from its roots, each node after its parent; kept when it
 * is a leaf the registered components' declarations use, or above one; named by its token. Whatever the tree - the
 * components, the roles, the states, the events, the extents - the same walk.
 */
public record ReadDual() implements StatelessFunctionalObject {

    public static final ReadDual INSTANCE = new ReadDual();

    /** Every tree the page names, in its order. */
    public List<DualTree<?>> trees() {
        return List.of(Components.INSTANCE, Roles.INSTANCE, States.INSTANCE, Events.INSTANCE, Extents.INSTANCE, Handles.INSTANCE);
    }

    /** A tree's dual, for the components the registration registers. */
    public <N> TreeDual read(DualTree<N> tree, Registration registration) {
        Set<N> used = tree.used(registration);
        var kept = new HashSet<N>();
        var all = new ArrayList<N>();
        for (N root : tree.roots(registration)) keep(tree, registration, root, used, kept, all);
        var nodes = new ArrayList<TreeDual.Node>();
        for (N root : tree.roots(registration)) walk(tree, registration, root, new ArrayList<>(), kept, nodes);
        List<String> left = all.stream().filter(n -> !kept.contains(n)).map(tree::token).toList();
        return new TreeDual(tree, nodes, all.size(), left);
    }

    /** Whether a node is kept: used, or above a used one. */
    private static <N> boolean keep(DualTree<N> tree, Registration registration, N node, Set<N> used, Set<N> kept, List<N> all) {
        all.add(node);
        boolean any = used.contains(node);
        for (N child : tree.children(registration, node)) any |= keep(tree, registration, child, used, kept, all);
        if (any) kept.add(node);
        return any;
    }

    private static <N> void walk(DualTree<N> tree, Registration registration, N node, List<N> above, Set<N> kept, List<TreeDual.Node> out) {
        if (!kept.contains(node)) return;
        Optional<String> parent = above.isEmpty() ? Optional.empty() : Optional.of(tree.name(above.getLast()));
        out.add(new TreeDual.Node(tree.name(node), tree.token(node), parent,
                tree.construct(registration, node, parent.orElse("null"), List.copyOf(above)), tree.refers(registration, node)));
        var down = new ArrayList<>(above);
        down.add(node);
        for (N child : tree.children(registration, node)) walk(tree, registration, child, down, kept, out);
    }
}
