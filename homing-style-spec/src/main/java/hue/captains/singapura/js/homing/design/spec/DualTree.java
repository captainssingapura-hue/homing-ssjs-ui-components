package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.EventBranch;
import hue.captains.singapura.js.homing.component.taxonomy.EventNode;
import hue.captains.singapura.js.homing.component.taxonomy.EventRoot;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Flag;
import hue.captains.singapura.js.homing.component.taxonomy.Importances;
import hue.captains.singapura.js.homing.component.taxonomy.Part;
import hue.captains.singapura.js.homing.component.taxonomy.Pseudo;
import hue.captains.singapura.js.homing.component.taxonomy.RoleBranch;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.StateBranch;
import hue.captains.singapura.js.homing.component.taxonomy.StateNode;
import hue.captains.singapura.js.homing.component.taxonomy.StateRoot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.StateValue;
import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.tree.NodeName;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * One Java tree read the one way every tree is (RFC 0071): its roots, each node's children, each node's token - unique in
 * its tree, the reader holds it so - what a node carries for the page, and the leaves the registered components'
 * declarations use. From it one generator makes the tree's JS dual ({@link ReadDual}, {@link DualScript}), and one pure
 * function says, for any node, which dual it is in and under which name ({@link DualRef}).
 *
 * @param <N> the tree's node type
 */
public sealed interface DualTree<N> extends StatelessFunctionalObject
        permits DualTree.Components, DualTree.Roles, DualTree.States, DualTree.Events, DualTree.Degrees {

    /** The tree's name, as the page reads it: {@code components}. */
    String name();

    /** What an imported node's name begins with, so two trees' nodes never share a name in one module: {@code Component}. */
    String prefix();

    /** Its roots: its root, or every value of a flat one. */
    List<N> roots(Taxonomy taxonomy);

    /** A node's children, branches first; none for a leaf. */
    List<N> children(Taxonomy taxonomy, N node);

    /** A node's token, unique in its tree. */
    String token(N node);

    /**
     * What a node carries for the page beyond its token and its parent - each a JS literal, by its key.
     *
     * @param above the node's ancestors, the root first
     */
    Map<String, String> carries(Taxonomy taxonomy, N node, List<N> above);

    /** The leaves the registered components' declarations use: what the dual keeps, with their ancestors, and nothing else. */
    Set<N> used(Taxonomy taxonomy);

    /** The dual's address: its class, as every module's is. */
    default String address() { return "/module?class=" + getClass().getName(); }

    /** The component tree: its branches and its components. */
    record Components() implements DualTree<Taxon> {
        public static final Components INSTANCE = new Components();
        @Override public String name() { return "components"; }
        @Override public String prefix() { return "Component"; }
        @Override public List<Taxon> roots(Taxonomy t) { return List.of(Root.INSTANCE); }
        @Override public List<Taxon> children(Taxonomy t, Taxon node) { return node instanceof ComponentBranch b ? t.children(b) : List.of(); }
        @Override public String token(Taxon node) { return node.token(); }
        @Override public Map<String, String> carries(Taxonomy t, Taxon node, List<Taxon> above) {
            return Map.of("kind", JsLiterals.INSTANCE.string(node instanceof ComponentBranch ? "branch" : node instanceof Composite<?> ? "composite" : "leaf"));
        }
        @Override public Set<Taxon> used(Taxonomy t) { return new LinkedHashSet<>(t.components()); }
    }

    /** The role catalogue: its branches and its roles - a role functional when a branch it is filed under says so. */
    record Roles() implements DualTree<RoleNode> {
        public static final Roles INSTANCE = new Roles();
        @Override public String name() { return "roles"; }
        @Override public String prefix() { return "Role"; }
        @Override public List<RoleNode> roots(Taxonomy t) { return List.of(RoleRoot.INSTANCE); }
        @Override public List<RoleNode> children(Taxonomy t, RoleNode node) { return node instanceof RoleBranch b ? t.children(b) : List.of(); }
        @Override public String token(RoleNode node) { return node.name().value(); }
        @Override public Map<String, String> carries(Taxonomy t, RoleNode node, List<RoleNode> above) {
            boolean functional = node instanceof RoleBranch b && b.functional();
            for (RoleNode a : above) if (a instanceof RoleBranch b && b.functional()) functional = true;
            return Map.of("functional", String.valueOf(functional));
        }
        @Override public Set<RoleNode> used(Taxonomy t) {
            var out = new LinkedHashSet<RoleNode>();
            for (Part<?, ?> p : t.parts()) out.add(p.role());
            return out;
        }
    }

    /** The tree of states: its branches and its state types, each with the states it allows. */
    record States() implements DualTree<StateNode> {
        public static final States INSTANCE = new States();
        @Override public String name() { return "states"; }
        @Override public String prefix() { return "State"; }
        @Override public List<StateNode> roots(Taxonomy t) { return List.of(StateRoot.INSTANCE); }
        @Override public List<StateNode> children(Taxonomy t, StateNode node) { return node instanceof StateBranch b ? t.states().children(b) : List.of(); }
        @Override public String token(StateNode node) { return node.name().value(); }
        @Override public Map<String, String> carries(Taxonomy t, StateNode node, List<StateNode> above) {
            if (!(node instanceof StateType<?> s)) return Map.of();
            var js = JsLiterals.INSTANCE;
            var out = new LinkedHashMap<String, String>();
            List<StateValue> values = s.values();
            out.put("states", js.strings(values.stream().map(StateValue::token).toList()));
            out.put("rest", js.string(values.getFirst().token()));
            var words = new LinkedHashMap<String, String>();
            for (StateValue v : values.subList(1, values.size())) words.put(v.token(), s.word(v));
            out.put("words", js.map(words));
            out.put("flag", String.valueOf(s instanceof Flag<?>));
            out.put("browser", s instanceof Pseudo p ? js.string(p.pseudoClass()) : "null");
            out.put("level", Importances.INSTANCE.levelOf(s).map(l -> js.string(NodeName.ofType(l, "").value())).orElse("null"));
            return out;
        }
        @Override public Set<StateNode> used(Taxonomy t) {
            var out = new LinkedHashSet<StateNode>();
            for (Component<?> c : t.components()) if (c.states() != null) out.addAll(c.states());
            return out;
        }
    }

    /** The tree of events: its branches and its event types. */
    record Events() implements DualTree<EventNode> {
        public static final Events INSTANCE = new Events();
        @Override public String name() { return "events"; }
        @Override public String prefix() { return "Event"; }
        @Override public List<EventNode> roots(Taxonomy t) { return List.of(EventRoot.INSTANCE); }
        @Override public List<EventNode> children(Taxonomy t, EventNode node) { return node instanceof EventBranch b ? t.events().children(b) : List.of(); }
        @Override public String token(EventNode node) { return node.name().value(); }
        @Override public Map<String, String> carries(Taxonomy t, EventNode node, List<EventNode> above) { return Map.of(); }
        @Override public Set<EventNode> used(Taxonomy t) {
            var out = new LinkedHashSet<EventNode>();
            for (Component<?> c : t.components()) out.addAll(c.events());
            return out;
        }
    }

    /** The degrees a component varies by: a flat tree, every value a root, each with its value at rest. */
    record Degrees() implements DualTree<ExtentAxis> {
        public static final Degrees INSTANCE = new Degrees();
        @Override public String name() { return "degrees"; }
        @Override public String prefix() { return "Degree"; }
        @Override public List<ExtentAxis> roots(Taxonomy t) { return List.of(ExtentAxis.values()); }
        @Override public List<ExtentAxis> children(Taxonomy t, ExtentAxis node) { return List.of(); }
        @Override public String token(ExtentAxis node) { return node.name().toLowerCase(Locale.ROOT); }
        @Override public Map<String, String> carries(Taxonomy t, ExtentAxis node, List<ExtentAxis> above) {
            return Map.of("rest", String.valueOf(node.rest()));
        }
        @Override public Set<ExtentAxis> used(Taxonomy t) {
            var out = new LinkedHashSet<ExtentAxis>();
            for (Component<?> c : t.components()) out.addAll(t.extents(c));
            return out;
        }
    }
}
