package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The one way a Java tree becomes its JS dual (RFC 0071): walked from its roots, each node after its parent; kept when it
 * is a leaf the registered components' declarations use, or above one; named by its token. Whatever the tree - the
 * components, the roles, the states, the events, the degrees - the same walk.
 */
public record ReadDual() implements StatelessFunctionalObject {

    public static final ReadDual INSTANCE = new ReadDual();

    /** Every tree the page names, in its order. */
    public List<DualTree<?>> trees() {
        return List.of(DualTree.Components.INSTANCE, DualTree.Roles.INSTANCE, DualTree.States.INSTANCE, DualTree.Events.INSTANCE,
                DualTree.Degrees.INSTANCE);
    }

    /** A tree's dual, for the components the taxonomy registers. */
    public <N> TreeDual read(DualTree<N> tree, Taxonomy taxonomy) {
        Set<N> used = tree.used(taxonomy);
        var kept = new HashSet<N>();
        var all = new ArrayList<N>();
        for (N root : tree.roots(taxonomy)) keep(tree, taxonomy, root, used, kept, all);
        var nodes = new ArrayList<TreeDual.Node>();
        for (N root : tree.roots(taxonomy)) walk(tree, taxonomy, root, new ArrayList<>(), kept, nodes);
        List<String> left = all.stream().filter(n -> !kept.contains(n)).map(tree::token).toList();
        return new TreeDual(tree, nodes, all.size(), left);
    }

    /** Whether a node is kept: used, or above a used one. */
    private static <N> boolean keep(DualTree<N> tree, Taxonomy taxonomy, N node, Set<N> used, Set<N> kept, List<N> all) {
        all.add(node);
        boolean any = used.contains(node);
        for (N child : tree.children(taxonomy, node)) any |= keep(tree, taxonomy, child, used, kept, all);
        if (any) kept.add(node);
        return any;
    }

    private static <N> void walk(DualTree<N> tree, Taxonomy taxonomy, N node, List<N> above, Set<N> kept, List<TreeDual.Node> out) {
        if (!kept.contains(node)) return;
        Optional<String> parent = above.isEmpty() ? Optional.empty() : Optional.of(JsLiterals.INSTANCE.name(tree.token(above.getLast())));
        out.add(new TreeDual.Node(JsLiterals.INSTANCE.name(tree.token(node)), tree.token(node), parent, tree.carries(taxonomy, node, List.copyOf(above))));
        var down = new ArrayList<>(above);
        down.add(node);
        for (N child : tree.children(taxonomy, node)) walk(tree, taxonomy, child, down, kept, out);
    }
}
