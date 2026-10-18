package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Flag;
import hue.captains.singapura.js.homing.component.taxonomy.Importances;
import hue.captains.singapura.js.homing.component.taxonomy.Pseudo;
import hue.captains.singapura.js.homing.component.taxonomy.StateBranch;
import hue.captains.singapura.js.homing.component.taxonomy.StateNode;
import hue.captains.singapura.js.homing.component.taxonomy.StateRoot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.StateValue;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;
import hue.captains.singapura.js.homing.tree.NodeName;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** The tree of states read for its dual (RFC 0071): its branches and its state types, each with the states it allows and what else it carries. */
public record States() implements DualTree<StateNode> {

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
