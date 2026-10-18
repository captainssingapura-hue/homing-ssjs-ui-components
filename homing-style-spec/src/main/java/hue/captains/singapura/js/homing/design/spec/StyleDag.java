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
 * A style's declarations and implementations as a graph (RFC 0067, D53): everything its elements reach, from each
 * top-level component's standalone - the style's, or the layer below's it falls to. Two levels, two kinds of pointer,
 * never confused: spec pointers from a declaration - to its own implementation, to a slot's target in the same role, or
 * to a placed part's standalone in the style worn - and delegation pointers from an implementation to the one whose CSS
 * it gives. Done right it is a DAG. An implementation needs CSS exactly where no delegation pointer leaves it.
 *
 * @param style    the style worn
 * @param nodes    every declaration and implementation reached, each once
 * @param pointers each spec pointer and each delegation pointer between them
 */
public record StyleDag(DesignStyle<?> style, List<Node> nodes, List<Pointer> pointers) implements ValueObject {

    public StyleDag {
        Objects.requireNonNull(style, "StyleDag.style");
        nodes = List.copyOf(nodes);
        pointers = List.copyOf(pointers);
    }

    /** What an implementation gives: CSS of its own, or one of the three delegations. */
    public enum Gives { CSS, DELEGATION, ROOT_DELEGATION, PARENT_DELEGATION }

    /** What a spec pointer points: a declaration's own implementation, a slot in its role, a placed slot standalone. */
    public enum Spec { OWN, SAME_ROLE, STANDALONE }

    /** A declaration or an implementation. */
    public sealed interface Node extends ValueObject permits DeclarationNode, ImplNode {

        /** Its layer's token and its class's simple name: {@code editorial/DialogDeclaration}. */
        String id();

        /** The style it is of: the style worn, or one below. */
        DesignStyle<?> layer();

        /** The casting it is of. */
        Casting casting();

        /** Whether it is a top-level component's standalone in the style worn. */
        boolean root();
    }

    /** A declaration: a composite's structure in a role. */
    public record DeclarationNode(String id, DesignStyle<?> layer, Casting casting, boolean root, CastingDeclaration<?, ?, ?, ?> declaration)
            implements Node {
        public DeclarationNode {
            Objects.requireNonNull(declaration, "DeclarationNode.declaration");
        }
    }

    /** An implementation: CSS, or a delegation. */
    public record ImplNode(String id, DesignStyle<?> layer, Casting casting, Gives gives, boolean root, CastingImplementation<?, ?, ?, ?> impl)
            implements Node {
        public ImplNode {
            Objects.requireNonNull(impl, "ImplNode.impl");
        }

        /** Whether it needs CSS of its own: no delegation pointer leaves it. */
        public boolean needsCss() { return gives == Gives.CSS; }
    }

    /** A pointer between two nodes: a spec pointer, or a delegation pointer. */
    public sealed interface Pointer extends ValueObject permits SpecPointer, DelegationPointer {

        /** The node pointing. */
        String from();

        /** The node pointed at. */
        String to();
    }

    /**
     * A declaration's pointer: its own implementation, a slot's target in the same role, or a placed part's standalone.
     *
     * @param from the declaration
     * @param to   what it reaches
     * @param spec which
     * @param slot the slot it points - for its own implementation, the declaration's own casting
     */
    public record SpecPointer(String from, String to, Spec spec, Casting slot) implements Pointer {
        public SpecPointer {
            Objects.requireNonNull(spec, "SpecPointer.spec");
            Objects.requireNonNull(slot, "SpecPointer.slot");
        }
    }

    /**
     * An implementation giving another's CSS.
     *
     * @param from  the implementation delegating
     * @param to    the implementation it gives the CSS of
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

    /** Its declarations. */
    public List<DeclarationNode> declarations() {
        return nodes.stream().filter(n -> n instanceof DeclarationNode).map(n -> (DeclarationNode) n).toList();
    }

    /** Its implementations. */
    public List<ImplNode> implementations() {
        return nodes.stream().filter(n -> n instanceof ImplNode).map(n -> (ImplNode) n).toList();
    }

    /** Its nodes in an order where every pointer points forward - a topological order - or empty when a cycle forbids one. */
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

    /** The deepest nesting of declarations: the longest path of spec pointers between declarations. */
    public int nesting() {
        var declared = new java.util.HashSet<String>();
        for (Node n : nodes) if (n instanceof DeclarationNode) declared.add(n.id());
        return longest(p -> p instanceof SpecPointer && declared.contains(p.to()));
    }

    /** The longest chain of delegation pointers alone: how far an implementation reaches for its CSS. */
    public int delegationLength() { return longest(p -> p instanceof DelegationPointer); }

    private int longest(Predicate<Pointer> kind) {
        List<Node> sorted = order().orElseThrow(() -> new IllegalStateException(style.token() + "'s graph holds a cycle"));
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

    /** The implementations that need CSS of their own - the graph's ends - of one layer. */
    public List<ImplNode> needingCss(DesignStyle<?> layer) {
        return implementations().stream().filter(n -> n.needsCss() && n.layer().equals(layer)).toList();
    }

    /** Each node with how many pointers of any kind point at it. */
    public Map<String, Integer> pointedAt() {
        var out = new LinkedHashMap<String, Integer>();
        for (Node n : nodes) out.put(n.id(), 0);
        for (Pointer p : pointers) out.merge(p.to(), 1, Integer::sum);
        return out;
    }
}
