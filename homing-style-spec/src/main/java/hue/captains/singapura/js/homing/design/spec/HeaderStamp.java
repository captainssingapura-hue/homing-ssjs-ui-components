package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * What the server stamps into a component's implementation module (RFC 0071, D19): its header, made from the component's
 * declarations alone. Each node it names - the component, its parts, their roles, its state types, its events, its
 * degrees - is imported from its tree's JS dual, the module and the name both a pure function of the node
 * ({@link DualRef}); then the header is one frozen object of those imports. No string is looked up and none is written
 * but a module's address.
 */
public record HeaderStamp() implements StatelessFunctionalObject {

    public static final HeaderStamp INSTANCE = new HeaderStamp();

    /** Every node the header names, each once, in its order: the component, each slot's part and role, its state types, events, degrees. */
    public List<DualRef.Ref> refs(ComponentHeader h) {
        var out = new LinkedHashMap<String, DualRef.Ref>();
        var refs = DualRef.INSTANCE;
        add(out, refs.of(h.component()));
        for (ComponentHeader.HeaderSlot s : h.slots()) { add(out, refs.of(s.part())); add(out, refs.of(s.role())); }
        for (ComponentHeader.HeaderState s : h.states()) add(out, refs.of(s.type()));
        for (EventType<?> e : h.events()) add(out, refs.of(e));
        for (ExtentAxis d : h.degrees()) add(out, refs.of(d));
        return List.copyOf(out.values());
    }

    /** The stamp as the module would begin: its imports, tree by tree, then its header. */
    public String stamp(ComponentHeader h) {
        var lines = new ArrayList<String>();
        lines.add("// Stamped by the server (RFC 0071, D19): " + h.component().token() + "'s header, made from its declarations - "
                + (h.composite() ? "a composite" : "a leaf") + (h.topLevel() ? ", top-level." : ", only ever a part."));
        List<DualRef.Ref> refs = refs(h);
        for (DualTree<?> tree : ReadDual.INSTANCE.trees()) {
            List<String> names = refs.stream().filter(r -> r.tree().equals(tree)).map(r -> r.name() + " as " + r.imported()).toList();
            if (!names.isEmpty()) lines.add("import { " + String.join(", ", names) + " } from " + JsLiterals.INSTANCE.string(tree.address()) + ";");
        }
        var refOf = DualRef.INSTANCE;
        var slots = new ArrayList<String>();
        for (ComponentHeader.HeaderSlot s : h.slots())
            slots.add("        Object.freeze({ part: " + refOf.of(s.part()).imported() + ", role: " + refOf.of(s.role()).imported()
                    + ", count: " + count(s.cardinality()) + " })");
        lines.add("const HEADER = Object.freeze({");
        lines.add("    component: " + refOf.of(h.component()).imported() + ",");
        lines.add("    slots: Object.freeze([" + (slots.isEmpty() ? "" : "\n" + String.join(",\n", slots) + "\n    ") + "]),");
        lines.add("    states: Object.freeze([" + String.join(", ", h.states().stream().map(s -> refOf.of(s.type()).imported()).toList()) + "]),");
        lines.add("    events: Object.freeze([" + String.join(", ", h.events().stream().map(e -> refOf.of(e).imported()).toList()) + "]),");
        lines.add("    degrees: Object.freeze([" + String.join(", ", h.degrees().stream().map(d -> refOf.of(d).imported()).toList()) + "])");
        lines.add("});");
        return String.join("\n", lines) + "\n";
    }

    private static void add(LinkedHashMap<String, DualRef.Ref> out, DualRef.Ref r) { out.putIfAbsent(r.imported(), r); }

    /** How many a part is, as data: its least and its most, none for no bound. */
    private static String count(Cardinality c) {
        return switch (c) {
            case Cardinality.Fixed f -> "Object.freeze({ min: " + f.count() + ", max: " + f.count() + " })";
            case Cardinality.Varying v -> "Object.freeze({ min: " + v.min() + ", max: " + switch (v.max()) {
                case Cardinality.AtMost a -> String.valueOf(a.count());
                case Cardinality.Unbounded u -> "null";
            } + " })";
        };
    }
}
