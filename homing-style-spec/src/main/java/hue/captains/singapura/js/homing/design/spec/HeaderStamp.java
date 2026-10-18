package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.js.homing.design.spec.dual.DualTree;
import hue.captains.singapura.js.homing.design.spec.page.PageStyleManager;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/**
 * What the server stamps into a component's implementation module (RFC 0071, D19): its header, made from the component's
 * declarations alone - nothing but names the implementation uses directly. Each node the component declares - itself, its
 * parts, their roles, its state types, its events, its extents - is imported from its tree's JS dual under its own name,
 * the module and the name both a pure function of the node ({@link DualRef}); each of its slots is a constant of its own,
 * made by the kit's constructor ({@link DualKit}). A top-level component's header also binds the page's style manager to
 * it: its implementation hands over its element at the root through that binding, and never imports the style manager.
 * No string is looked up, and none is written but a module's address.
 */
public record HeaderStamp() implements StatelessFunctionalObject {

    public static final HeaderStamp INSTANCE = new HeaderStamp();

    /** One constant a slot is stamped as: its name, and its line. */
    public record SlotConstant(String name, String line) {}

    /** Every node the header imports, each once, in its order: the component, each slot's part and role, its state types, events, extents. */
    public List<DualRef.Ref> refs(ComponentHeader h) {
        var out = new LinkedHashMap<String, DualRef.Ref>();
        var refs = DualRef.INSTANCE;
        add(out, refs.of(h.component()));
        for (ComponentHeader.HeaderSlot s : h.slots()) { add(out, refs.of(s.part())); add(out, refs.of(s.role())); }
        for (ComponentHeader.HeaderState s : h.states()) add(out, refs.of(s.type()));
        for (EventType<?> e : h.events()) add(out, refs.of(e));
        for (ExtentAxis x : h.extents()) add(out, refs.of(x));
        return List.copyOf(out.values());
    }

    /** Each slot as its own constant, made by the kit: its owner, its part and its role - the imported nodes - and how many the part is. */
    public List<SlotConstant> slots(ComponentHeader h) {
        var refOf = DualRef.INSTANCE;
        var out = new ArrayList<SlotConstant>();
        for (ComponentHeader.HeaderSlot s : h.slots()) {
            String name = "SLOT_" + s.token().toUpperCase(Locale.ROOT).replace('-', '_');
            out.add(new SlotConstant(name, "const " + name + " = new Slot(" + refOf.of(h.component()).imported() + ", " + refOf.of(s.part()).imported()
                    + ", " + refOf.of(s.role()).imported() + ", " + count(s.cardinality()) + ");"));
        }
        return List.copyOf(out);
    }

    /**
     * The name a top-level component's binding is stamped under: {@code manage}, then the component's name -
     * {@code manageDialog}. One for each component a module implements, so several never meet.
     */
    public String manage(ComponentHeader h) { return "manage" + JsLiterals.INSTANCE.name(h.component().token()); }

    /**
     * The stamp as the module would begin: its imports, tree by tree, then a constant for each slot; and, for a top-level
     * component, the page's style manager imported and bound to it - {@code const manageDialog =
     * styleManager.unit(ComponentDialog);} - the one way its implementation hands over its element at the root. A
     * component only ever a part has none: its owner hands it its reference.
     */
    public String stamp(ComponentHeader h, PageStyleManager page) {
        var js = JsLiterals.INSTANCE;
        var lines = new ArrayList<String>();
        lines.add("// Stamped by the server (RFC 0071, D19): " + h.component().token() + "'s header, made from its declarations - "
                + (h.composite() ? "a composite" : "a leaf") + (h.topLevel() ? ", top-level." : ", only ever a part."));
        if (!h.slots().isEmpty()) lines.add("import { Slot, Count } from " + js.string(DualKit.INSTANCE.address()) + ";");
        List<DualRef.Ref> refs = refs(h);
        for (DualTree<?> tree : ReadDual.INSTANCE.trees()) {
            List<String> names = refs.stream().filter(r -> r.tree().equals(tree)).map(r -> r.name() + " as " + r.imported()).toList();
            if (!names.isEmpty()) lines.add("import { " + String.join(", ", names) + " } from " + js.string(tree.address()) + ";");
        }
        if (h.topLevel()) lines.add("import { " + PageStyleManager.EXPORTED + " } from " + js.string(page.address()) + ";");
        for (SlotConstant s : slots(h)) lines.add(s.line());
        if (h.topLevel())
            lines.add("const " + manage(h) + " = " + PageStyleManager.EXPORTED + ".unit(" + DualRef.INSTANCE.of(h.component()).imported() + ");");
        return String.join("\n", lines) + "\n";
    }

    private static void add(LinkedHashMap<String, DualRef.Ref> out, DualRef.Ref r) { out.putIfAbsent(r.imported(), r); }

    /** How many a part is, made by the kit: its least and its most, none for no bound. */
    private static String count(Cardinality c) {
        return switch (c) {
            case Cardinality.Fixed f -> "new Count(" + f.count() + ", " + f.count() + ")";
            case Cardinality.Varying v -> "new Count(" + v.min() + ", " + switch (v.max()) {
                case Cardinality.AtMost a -> String.valueOf(a.count());
                case Cardinality.Unbounded u -> "null";
            } + ")";
        };
    }
}
