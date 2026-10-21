package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Classed;
import hue.captains.singapura.js.homing.component.taxonomy.Flag;
import hue.captains.singapura.js.homing.component.taxonomy.NamedChange;
import hue.captains.singapura.js.homing.component.taxonomy.StateDomain;
import hue.captains.singapura.js.homing.component.taxonomy.Importances;
import hue.captains.singapura.js.homing.component.taxonomy.Pseudo;
import hue.captains.singapura.js.homing.component.taxonomy.StateBranch;
import hue.captains.singapura.js.homing.component.taxonomy.StateNode;
import hue.captains.singapura.js.homing.component.taxonomy.StateRoot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.StateValue;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;
import hue.captains.singapura.js.homing.tree.NodeName;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** The tree of states read for its dual (RFC 0071): its branches and its state types, each with the states it allows and what else it carries. */
public record States() implements DualTree<StateNode> {

    public static final States INSTANCE = new States();

    @Override public String name() { return "states"; }
    @Override public String prefix() { return "State"; }
    @Override public List<StateNode> roots(Registration r) { return List.of(StateRoot.INSTANCE); }
    @Override public List<StateNode> children(Registration r, StateNode node) { return node instanceof StateBranch b ? r.taxonomy().states().children(b) : List.of(); }
    @Override public String token(StateNode node) { return node.name().value(); }
    @Override public String construct(Registration r, StateNode node, String parent, List<StateNode> above) {
        var js = JsLiterals.INSTANCE;
        String token = js.string(token(node));
        if (!(node instanceof StateType<?> s)) return "new StateBranch(" + token + ", " + parent + ")";
        String classes = "null";
        if (s instanceof Classed<?> c) {   // its look a class: its words, whether a yes or a no, the browser's pseudo-class, its level
            List<StateValue> values = c.values();
            var words = new LinkedHashMap<String, String>();
            for (StateValue v : values.subList(1, values.size())) words.put(v.token(), c.word(v));
            String level = Importances.INSTANCE.levelOf(c).map(l -> js.string(NodeName.ofType(l, "").value())).orElse("null");
            classes = "new StateClasses(" + js.object(words) + ", " + (c instanceof Flag<?>) + ", " + (c instanceof Pseudo p ? js.string(p.pseudoClass()) : "null") + ", " + level + ")";
        }
        String changes = String.join(", ", s.changes().stream().map(States::change).toList());
        return "new StateType(" + token + ", " + parent + ", " + domain(s.domain()) + ", [" + changes + "], " + classes + ")";
    }

    /** A domain as the kit makes it: {@code new Ranges([new Point("up"), new Point("down")], "up")}, {@code new Valued("text")}. */
    private static String domain(StateDomain d) {
        var js = JsLiterals.INSTANCE;
        return switch (d) {
            case StateDomain.Ranges r -> "new Ranges([" + String.join(", ", r.ranges().stream().map(x -> switch (x) {
                case StateDomain.Point p -> "new Point(" + js.string(p.value().token()) + ")";
                case StateDomain.Span s -> "new Span(" + number(s.least()) + ", " + number(s.most()) + ", " + s.whole() + ")";
            }).toList()) + "], " + value(r.rest()) + ")";
            case StateDomain.Valued v -> "new Valued(" + js.string(v.kind().name().toLowerCase(java.util.Locale.ROOT)) + ")";
        };
    }

    /** A value as the page has it: a named one its token, a number itself, a text a string. */
    private static String value(StateDomain.Value v) {
        return switch (v) {
            case StateDomain.Named n -> JsLiterals.INSTANCE.string(n.value().token());
            case StateDomain.Number n -> number(n.value());
            case StateDomain.Text t -> JsLiterals.INSTANCE.string(t.value());
        };
    }

    /** A named change as the kit makes it: {@code new NamedChange("press", new Between("up", "down"))}. */
    private static String change(NamedChange c) {
        var js = JsLiterals.INSTANCE;
        return "new NamedChange(" + js.string(c.name()) + ", " + switch (c.transition()) {
            case NamedChange.Between b -> "new Between(" + js.string(b.from().token()) + ", " + js.string(b.to().token()) + ")";
            case NamedChange.Rises r -> "new Rises()";
            case NamedChange.Falls f -> "new Falls()";
        } + ")";
    }

    private static String number(double d) { return d == Math.rint(d) && Math.abs(d) < 1e15 ? String.valueOf((long) d) : String.valueOf(d); }

    @Override public List<String> kit() { return List.of("StateBranch", "StateType", "Point", "Span", "Ranges", "Valued", "Between", "Rises", "Falls", "NamedChange", "StateClasses"); }
    @Override public Set<StateNode> used(Registration r) {
        var out = new LinkedHashSet<StateNode>();
        for (Component<?> c : r.taxonomy().components()) if (c.states() != null) out.addAll(c.states());
        return out;
    }
}
