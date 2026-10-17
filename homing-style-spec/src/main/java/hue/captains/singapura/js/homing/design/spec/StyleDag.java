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
import java.util.function.Predicate;

/**
 * A style's looks as a graph (RFC 0067, D53): every look its elements reach, from each top-level component's
 * implementation - the style's, or the layer below's it falls to - along two kinds of pointer, never confused: a spec
 * pointer, from a look to the look a part slot of it wears - the model's structure; and a delegation pointer, from a
 * look to the look whose CSS it gives - how little is written. Done right it is a DAG, its layers the style's and those
 * below, nothing pointing up. A look needs CSS exactly where no delegation pointer leaves it - an end - and the style's
 * author writes the ends of the style's own layer.
 *
 * @param style    the style worn
 * @param nodes    every look reached, each once
 * @param pointers each spec pointer and each delegation pointer between them
 */
public record StyleDag(DesignStyle<?> style, List<Node> nodes, List<Pointer> pointers) implements ValueObject {

    public StyleDag {
        Objects.requireNonNull(style, "StyleDag.style");
        nodes = List.copyOf(nodes);
        pointers = List.copyOf(pointers);
    }

    /** What a look gives: CSS of its own, or one of the three delegations. */
    public enum Gives { CSS, DELEGATION, ROOT_DELEGATION, PARENT_DELEGATION }

    /**
     * A look.
     *
     * @param id      its layer's token and its class's simple name: {@code editorial/PlainButtonAction}
     * @param layer   the style it is of: the style worn, or one below
     * @param casting the casting it is a look of
     * @param gives   CSS of its own, or a delegation
     * @param root    whether it is a top-level component's implementation in the style worn
     * @param look    the look itself
     */
    public record Node(String id, DesignStyle<?> layer, Casting casting, Gives gives, boolean root, CastingStylePerRole<?, ?, ?, ?> look)
            implements ValueObject {
        public Node {
            Objects.requireNonNull(look, "Node.look");
        }

        /** Whether it needs CSS of its own: no delegation pointer leaves it, an end of the graph. */
        public boolean needsCss() { return gives == Gives.CSS; }
    }

    /** A pointer from one look to another: a spec pointer, or a delegation pointer. */
    public sealed interface Pointer extends ValueObject permits SpecPointer, DelegationPointer {

        /** The look pointing. */
        String from();

        /** The look pointed at. */
        String to();
    }

    /**
     * A part slot of the look pointing, pointed at the look the part wears there: the model's structure, held complete.
     *
     * @param from the owner's look
     * @param to   the look the part wears
     * @param slot the slot: the part in the role its owner declares
     */
    public record SpecPointer(String from, String to, Casting slot) implements Pointer {
        public SpecPointer {
            Objects.requireNonNull(slot, "SpecPointer.slot");
        }
    }

    /**
     * A look giving another look's CSS: the same component's, in the same role in its style or the one below, or on its
     * own in its style.
     *
     * @param from  the look delegating
     * @param to    the look it gives the CSS of
     * @param gives which delegation: never CSS
     */
    public record DelegationPointer(String from, String to, Gives gives) implements Pointer {
        public DelegationPointer {
            if (gives == Gives.CSS) throw new IllegalArgumentException(from + ": a delegation pointer is a delegation, not CSS");
        }
    }

    /** Its spec pointers. */
    public List<SpecPointer> specs() {
        return pointers.stream().filter(p -> p instanceof SpecPointer).map(p -> (SpecPointer) p).toList();
    }

    /** Its delegation pointers. */
    public List<DelegationPointer> delegations() {
        return pointers.stream().filter(p -> p instanceof DelegationPointer).map(p -> (DelegationPointer) p).toList();
    }

    /** Its looks in an order where every pointer points forward - a topological order - or empty when a cycle forbids one. */
    public Optional<List<Node>> order() {
        var byId = new LinkedHashMap<String, Node>();
        for (Node n : nodes) byId.put(n.id(), n);
        var into = new HashMap<String, Integer>();
        var out = new HashMap<String, List<String>>();
        for (Node n : nodes) into.put(n.id(), 0);
        for (Pointer p : pointers) {
            into.merge(p.to(), 1, Integer::sum);
            out.computeIfAbsent(p.from(), k -> new ArrayList<>()).add(p.to());
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

    /** The longest path's length, in pointers of any kind, when it is a DAG. */
    public int depth() { return longest(p -> true); }

    /** The deepest nesting of parts: the longest path of spec pointers alone. */
    public int specDepth() { return longest(p -> p instanceof SpecPointer); }

    /** The longest chain of delegation pointers alone: how far a look reaches for its CSS. */
    public int delegationLength() { return longest(p -> p instanceof DelegationPointer); }

    private int longest(Predicate<Pointer> kind) {
        List<Node> sorted = order().orElseThrow(() -> new IllegalStateException(style.token() + "'s looks hold a cycle"));
        var longest = new HashMap<String, Integer>();
        var out = new HashMap<String, List<String>>();
        for (Pointer p : pointers) if (kind.test(p)) out.computeIfAbsent(p.from(), k -> new ArrayList<>()).add(p.to());
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

    /** The looks that need CSS of their own - the graph's ends - of one layer. */
    public List<Node> needingCss(DesignStyle<?> layer) {
        return nodes.stream().filter(n -> n.needsCss() && n.layer().equals(layer)).toList();
    }

    /** Each look with how many pointers of any kind point at it. */
    public Map<String, Integer> pointedAt() {
        var out = new LinkedHashMap<String, Integer>();
        for (Node n : nodes) out.put(n.id(), 0);
        for (Pointer p : pointers) out.merge(p.to(), 1, Integer::sum);
        return out;
    }
}
