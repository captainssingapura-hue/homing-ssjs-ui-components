package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * A style's looks as a graph (RFC 0067, D53): every look its elements reach, from each top-level component's
 * implementation - the style's, or the layer below's it falls to - down the parts' specs and along the delegations. Done
 * right it is a DAG, its layers the style's and those below, nothing pointing up.
 *
 * @param style the style worn
 * @param nodes every look reached, each once
 * @param edges each spec and each delegation between them
 */
public record StyleDag(DesignStyle<?> style, List<Node> nodes, List<Edge> edges) implements ValueObject {

    public StyleDag {
        Objects.requireNonNull(style, "StyleDag.style");
        nodes = List.copyOf(nodes);
        edges = List.copyOf(edges);
    }

    /** What a look gives. */
    public enum Gives { CSS, DELEGATION, PARENT_DELEGATION }

    /** What an edge is. */
    public enum Points { PART, DELEGATION, PARENT_DELEGATION }

    /**
     * A look.
     *
     * @param id      its layer's token and its class's simple name: {@code editorial/PlainButtonAction}
     * @param layer   the style it is of: the style worn, or one below
     * @param casting the casting it is a look of
     * @param gives   CSS of its own, or a delegation
     * @param root    whether it is a top-level component's implementation in the style worn
     */
    public record Node(String id, DesignStyle<?> layer, Casting casting, Gives gives, boolean root) implements ValueObject {}

    /**
     * A spec or a delegation.
     *
     * @param from   the look pointing
     * @param to     the look pointed at
     * @param points what it is
     * @param slot   for a spec, the slot it points: the part in its declared role; else null
     */
    public record Edge(String from, String to, Points points, Casting slot) implements ValueObject {}

    /** Its looks in an order where every edge points forward - a topological order - or empty when a cycle forbids one. */
    public Optional<List<Node>> order() {
        var byId = new LinkedHashMap<String, Node>();
        for (Node n : nodes) byId.put(n.id(), n);
        var into = new HashMap<String, Integer>();
        var out = new HashMap<String, List<String>>();
        for (Node n : nodes) into.put(n.id(), 0);
        for (Edge e : edges) {
            into.merge(e.to(), 1, Integer::sum);
            out.computeIfAbsent(e.from(), k -> new ArrayList<>()).add(e.to());
        }
        var ready = new ArrayDeque<String>();
        for (Node n : nodes) if (into.get(n.id()) == 0) ready.add(n.id());
        var sorted = new ArrayList<Node>();
        while (!ready.isEmpty()) {
            String id = ready.poll();
            sorted.add(byId.get(id));
            for (String to : out.getOrDefault(id, List.of())) if (into.merge(to, -1, Integer::sum) == 0) ready.add(to);
        }
        return sorted.size() == nodes.size() ? Optional.of(List.copyOf(sorted)) : Optional.empty();
    }

    /** The longest path's length, in edges, when it is a DAG. */
    public int depth() {
        List<Node> sorted = order().orElseThrow(() -> new IllegalStateException(style.token() + "'s looks hold a cycle"));
        var longest = new HashMap<String, Integer>();
        var out = new HashMap<String, List<String>>();
        for (Edge e : edges) out.computeIfAbsent(e.from(), k -> new ArrayList<>()).add(e.to());
        int max = 0;
        for (int i = sorted.size() - 1; i >= 0; i--) {
            String id = sorted.get(i).id();
            int d = 0;
            for (String to : out.getOrDefault(id, List.of())) d = Math.max(d, longest.get(to) + 1);
            longest.put(id, d);
            max = Math.max(max, d);
        }
        return max;
    }

    /** Each look with how many edges point at it. */
    public Map<String, Integer> pointedAt() {
        var out = new LinkedHashMap<String, Integer>();
        for (Node n : nodes) out.put(n.id(), 0);
        for (Edge e : edges) out.merge(e.to(), 1, Integer::sum);
        return out;
    }
}
