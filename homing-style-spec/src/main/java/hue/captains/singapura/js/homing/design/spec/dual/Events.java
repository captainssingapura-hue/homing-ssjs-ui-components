package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.EventBranch;
import hue.captains.singapura.js.homing.component.taxonomy.EventNode;
import hue.captains.singapura.js.homing.component.taxonomy.EventRoot;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** The tree of events read for its dual (RFC 0071): its branches and its event types. */
public record Events() implements DualTree<EventNode> {

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
