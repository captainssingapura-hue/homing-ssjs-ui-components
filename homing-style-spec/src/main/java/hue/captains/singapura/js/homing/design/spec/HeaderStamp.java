package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.StateValue;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * What the server would stamp into a component's implementation module (RFC 0071, D19, D20) - a sketch of the shape, the
 * shape itself still open (RFC 0071, open point 3). The module asks the style manager for its own header, by its own
 * address, and binds a name to each object the style manager made for the component: itself, each slot, each part, each
 * state type, each event and degree. Each name the token's, upper case; nothing in it names a class.
 */
public record HeaderStamp() implements StatelessFunctionalObject {

    public static final HeaderStamp INSTANCE = new HeaderStamp();

    /** One binding of the stamp: the name the module's code uses, and what it is bound to. */
    public record Binding(String name, String line) {}

    /** The stamp's bindings, in its order: the component, its slots, its parts, its state types, its events, its degrees. */
    public List<Binding> bindings(ComponentHeader h) {
        var out = new ArrayList<Binding>();
        String self = constant(h.component().token());
        out.add(new Binding(self, "const " + self + " = styleManager.header(import.meta.url).component(" + quoted(h.component().token()) + ");"));
        for (ComponentHeader.HeaderSlot s : h.slots())
            out.add(new Binding("SLOT_" + constant(s.token()), "const SLOT_" + constant(s.token()) + " = " + self + ".slot(" + quoted(s.token()) + ");"
                    + "   // " + s.part().token() + " as " + s.role().name().value() + ", " + many(s.cardinality())
                    + (s.placed() ? ", placed - its own standalone" : "") + (s.alternatives() > 1 ? ", one of " + s.alternatives() : "")));
        for (var p : h.parts())
            out.add(new Binding("PART_" + constant(p.token()), "const PART_" + constant(p.token()) + " = " + self + ".part(" + quoted(p.token()) + ");"));
        for (ComponentHeader.HeaderState st : h.states())
            out.add(new Binding("STATE_" + constant(st.token()), "const STATE_" + constant(st.token()) + " = " + self + ".state(" + quoted(st.token()) + ");"
                    + "   // " + String.join(" | ", st.allowed().stream().map(StateValue::token).toList()) + ", at rest " + st.rest().token()
                    + st.browser().map(b -> " - the browser's (" + b + "), never made here").orElse("")));
        for (EventType<?> e : h.events()) {
            String t = e.name().value();
            out.add(new Binding("EVENT_" + constant(t), "const EVENT_" + constant(t) + " = " + self + ".event(" + quoted(t) + ");"));
        }
        for (ExtentAxis d : h.degrees()) {
            String t = d.name().toLowerCase(Locale.ROOT);
            out.add(new Binding("DEGREE_" + constant(t), "const DEGREE_" + constant(t) + " = " + self + ".degree(" + quoted(t) + ");"));
        }
        return List.copyOf(out);
    }

    /** The stamp as the module would begin. */
    public String stamp(ComponentHeader h) {
        var lines = new ArrayList<String>();
        lines.add("// Stamped by the server (RFC 0071, D19): " + h.component().token() + "'s header, derived from the taxonomy as the module");
        lines.add("// is served - " + (h.composite() ? "a composite" : "a leaf") + (h.topLevel() ? ", top-level" : ", only ever a part")
                + ". Every object the style manager's own; the module's code begins below it.");
        for (Binding b : bindings(h)) lines.add(b.line());
        return String.join("\n", lines) + "\n";
    }

    private static String constant(String token) { return token.toUpperCase(Locale.ROOT).replace('-', '_'); }

    private static String quoted(String s) { return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\""; }

    private static String many(Cardinality c) {
        return switch (c) {
            case Cardinality.Fixed f -> f.count() == 1 ? "one" : f.count() + "";
            case Cardinality.Varying v -> v.min() + " or more" + switch (v.max()) {
                case Cardinality.AtMost a -> ", at most " + a.count();
                case Cardinality.Unbounded u -> "";
            };
        };
    }
}
