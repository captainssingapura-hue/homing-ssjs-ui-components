package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Pseudo;
import hue.captains.singapura.js.homing.component.taxonomy.Room;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.spec.dual.Components;
import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.js.homing.design.spec.dual.DualTree;
import hue.captains.singapura.js.homing.design.spec.page.PageStyleManager;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
        // what its parts fire and change, so an owner can handle each - a press on the button it holds (port journal)
        for (Component<?> p : h.parts()) {
            for (EventType<?> e : p.events()) add(out, refs.of(e));
            for (var s : p.states()) if (!(s instanceof Pseudo)) add(out, refs.of(s));
        }
        return List.copyOf(out.values());
    }

    /** Each room of the component's own as a constant - {@code const ROOM_TAIL = RoomTabStripTail;} - its node in the components' dual. */
    public List<SlotConstant> rooms(ComponentHeader h) {
        var out = new ArrayList<SlotConstant>();
        for (Room room : h.rooms()) {
            String name = "ROOM_" + room.name().toUpperCase(Locale.ROOT).replace('-', '_');
            out.add(new SlotConstant(name, "const " + name + " = " + Components.INSTANCE.room(h.component(), room) + ";"));
        }
        return List.copyOf(out);
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
     * Each slot as its constant in a merged stamp: its owner's name before the slot's - {@code SLOT_CLOSE_BUTTON_CLOSE_ICON_FACE}
     * - since two owners under one branch may each have a slot of one token, the close button's face and the icon button's.
     */
    public List<SlotConstant> merged(ComponentHeader h) {
        String owner = h.component().token().toUpperCase(Locale.ROOT).replace('-', '_');
        return slots(h).stream().map(s -> {
            String name = "SLOT_" + owner + "_" + s.name().substring("SLOT_".length());
            return new SlotConstant(name, "const " + name + s.line().substring(("const " + s.name()).length()));
        }).toList();
    }

    /** Each slot's and each room's constant in a merged stamp, its owner's name before its own: {@code ROOM_PANEL_BODY}. */
    private List<SlotConstant> mergedAll(ComponentHeader h) {
        String owner = h.component().token().toUpperCase(Locale.ROOT).replace('-', '_');
        var out = new ArrayList<>(merged(h));
        for (SlotConstant s : rooms(h)) {
            String name = "ROOM_" + owner + "_" + s.name().substring("ROOM_".length());
            out.add(new SlotConstant(name, "const " + name + s.line().substring(("const " + s.name()).length())));
        }
        return out;
    }

    /** The room nodes a header imports from the components' dual: {@code RoomTabStripTail}. */
    public List<String> roomNodes(ComponentHeader h) { return h.rooms().stream().map(r -> Components.INSTANCE.room(h.component(), r)).toList(); }

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
            var names = new ArrayList<>(refs.stream().filter(r -> r.tree().equals(tree)).map(r -> r.name() + " as " + r.imported()).toList());
            if (tree instanceof Components) { names.addAll(fixed(h)); names.addAll(roomNodes(h)); }
            if (!names.isEmpty()) lines.add("import { " + String.join(", ", names) + " } from " + js.string(tree.address()) + ";");
        }
        if (h.topLevel()) lines.add("import { " + PageStyleManager.EXPORTED + " } from " + js.string(page.address()) + ";");
        for (SlotConstant s : slots(h)) lines.add(s.line());
        for (SlotConstant s : rooms(h)) lines.add(s.line());
        if (h.topLevel())
            lines.add("const " + manage(h) + " = " + PageStyleManager.EXPORTED + ".unit(" + DualRef.INSTANCE.of(h.component()).imported() + ");");
        return String.join("\n", lines) + "\n";
    }

    /**
     * The stamp of a module that implements several components - a branch's base, every leaf under it; the icons' one
     * class - their headers merged (D19): each tree's imports once, two headers naming one node; the kit's and the page's
     * style manager's once; each slot's constant once, its owner's name before it ({@link #merged}); a
     * binding for each top-level component, each under its own name. One header is stamped as it is.
     */
    public String stamp(List<ComponentHeader> headers, PageStyleManager page) {
        if (headers.isEmpty()) throw new IllegalArgumentException("a stamp is of one header at least");
        if (headers.size() == 1) return stamp(headers.getFirst(), page);
        var js = JsLiterals.INSTANCE;
        var lines = new ArrayList<String>();
        lines.add("// Stamped by the server (RFC 0071, D19): the headers of " + String.join(", ", headers.stream().map(h -> h.component().token()).toList())
                + ", merged - each made from its declarations.");
        if (headers.stream().anyMatch(h -> !h.slots().isEmpty())) lines.add("import { Slot, Count } from " + js.string(DualKit.INSTANCE.address()) + ";");
        for (DualTree<?> tree : ReadDual.INSTANCE.trees()) {
            var names = new LinkedHashSet<String>();
            for (ComponentHeader h : headers) {
                for (DualRef.Ref r : refs(h)) if (r.tree().equals(tree)) names.add(r.name() + " as " + r.imported());
                if (tree instanceof Components) { names.addAll(fixed(h)); names.addAll(roomNodes(h)); }
            }
            if (!names.isEmpty()) lines.add("import { " + String.join(", ", names) + " } from " + js.string(tree.address()) + ";");
        }
        if (headers.stream().anyMatch(ComponentHeader::topLevel)) lines.add("import { " + PageStyleManager.EXPORTED + " } from " + js.string(page.address()) + ";");
        var constants = new LinkedHashMap<String, String>();
        for (ComponentHeader h : headers) for (SlotConstant s : mergedAll(h)) {
            String was = constants.putIfAbsent(s.name(), s.line());
            if (was != null && !was.equals(s.line()))
                throw new IllegalArgumentException("two slots stamped as " + s.name() + " in one module: " + was + " and " + s.line());
        }
        lines.addAll(constants.values());
        for (ComponentHeader h : headers) if (h.topLevel())
            lines.add("const " + manage(h) + " = " + PageStyleManager.EXPORTED + ".unit(" + DualRef.INSTANCE.of(h.component()).imported() + ");");
        return String.join("\n", lines) + "\n";
    }

    /**
     * For the one class that realizes every leaf under a branch - the icons' ({@code ImplForLeavesOf}) - the leaves it is
     * stamped with, after its merged stamp: each leaf's node, its fixed type and its binding, none for a leaf only ever a
     * part - {@code [ComponentCloseIcon, FixedCloseIcon, manageCloseIcon]}. The class tells which leaf it is made as by
     * the type of the fixed object it is handed, and places itself at the root with that leaf's binding. Nothing else
     * says which binding is whose: they are the module's own names.
     */
    public String leaves(List<ComponentHeader> headers) {
        var rows = new ArrayList<String>();
        for (ComponentHeader h : headers)
            rows.add("[" + DualRef.INSTANCE.of(h.component()).imported() + ", " + FixedTypes.INSTANCE.name(h.component()) + ", "
                    + (h.topLevel() ? manage(h) : "null") + "]");
        return "const " + LEAVES + " = [" + String.join(", ", rows) + "];\n";
    }

    /** The name the leaves a one-class module realizes are stamped under. */
    public static final String LEAVES = "LEAVES_IMPLEMENTED";

    /** The fixed types it imports (D30): its component's, then each part's, each once - every one an owner may make. */
    public List<String> fixed(ComponentHeader h) {
        var out = new LinkedHashSet<String>();
        out.add(FixedTypes.INSTANCE.name(h.component()));
        for (ComponentHeader.HeaderSlot s : h.slots()) out.add(FixedTypes.INSTANCE.name(s.part()));
        return List.copyOf(out);
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
