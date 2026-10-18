package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.EventBranch;
import hue.captains.singapura.js.homing.component.taxonomy.EventNode;
import hue.captains.singapura.js.homing.component.taxonomy.EventRoot;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** The tree of events read for its dual (RFC 0071): its branches and its event types. */
public record Events() implements DualTree<EventNode> {

    public static final Events INSTANCE = new Events();

    @Override public String name() { return "events"; }
    @Override public String prefix() { return "Event"; }
    @Override public List<EventNode> roots(Taxonomy t) { return List.of(EventRoot.INSTANCE); }
    @Override public List<EventNode> children(Taxonomy t, EventNode node) { return node instanceof EventBranch b ? t.events().children(b) : List.of(); }
    @Override public String token(EventNode node) { return node.name().value(); }
    @Override public String construct(Taxonomy t, EventNode node, String parent, List<EventNode> above) {
        return "new " + (node instanceof EventBranch ? "EventBranch" : "EventType") + "(" + JsLiterals.INSTANCE.string(token(node)) + ", " + parent + ")";
    }
    @Override public List<String> kit() { return List.of("EventBranch", "EventType"); }
    @Override public Set<EventNode> used(Taxonomy t) {
        var out = new LinkedHashSet<EventNode>();
        for (Component<?> c : t.components()) out.addAll(c.events());
        return out;
    }
}
