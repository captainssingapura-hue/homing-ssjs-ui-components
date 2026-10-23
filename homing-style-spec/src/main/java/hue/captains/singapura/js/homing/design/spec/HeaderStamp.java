package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Pseudo;
import hue.captains.singapura.js.homing.component.taxonomy.Room;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.spec.dual.Components;
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
 * parts, their roles, its state types, its extents - is imported from its tree's JS dual under its own name,
 * the module and the name both a pure function of the node ({@link DualRef}); and its parts' fixed types, by which it
 * places each part it makes - {@code this._place(FixedCloseIcon)}, its role said only where one player fills two places
 * of it ({@code this._place(FixedPlainButton, RoleZoomIn)}). No slot is stamped (owner, 21 Oct): a module implements one
 * component, so the owner is the module's own, and the part is the one it makes. A top-level component's header also
 * binds the page's style manager to it: its implementation hands over its element at the root through that binding, and
 * never imports the style manager. No string is looked up, and none is written but a module's address.
 */
public record HeaderStamp() implements StatelessFunctionalObject {

    public static final HeaderStamp INSTANCE = new HeaderStamp();

    /** One constant a header stamps - a room's: its name, and its line. */
    public record Constant(String name, String line) {}

    /** Every node the header imports, each once, in its order: the component, each slot's part and role, its state types, extents. */
    public List<DualRef.Ref> refs(ComponentHeader h) {
        var out = new LinkedHashMap<String, DualRef.Ref>();
        var refs = DualRef.INSTANCE;
        add(out, refs.of(h.component()));
        for (ComponentHeader.HeaderSlot s : h.slots()) { add(out, refs.of(s.part())); add(out, refs.of(s.role())); }
        for (ComponentHeader.HeaderState s : h.states()) add(out, refs.of(s.type()));
        for (ExtentAxis x : h.extents()) add(out, refs.of(x));
        // what its parts' states are, so an owner can hear each change - its button's clicks (RFC 0069, amended)
        for (Component<?> p : h.parts())
            for (var s : p.states()) if (!(s instanceof Pseudo)) add(out, refs.of(s));
        return List.copyOf(out.values());
    }

    /** Each room of the component's own as a constant - {@code const ROOM_TAIL = RoomTabStripTail;} - its node in the components' dual. */
    public List<Constant> rooms(ComponentHeader h) {
        var out = new ArrayList<Constant>();
        for (Room room : h.rooms()) {
            String name = "ROOM_" + room.name().toUpperCase(Locale.ROOT).replace('-', '_');
            out.add(new Constant(name, "const " + name + " = " + Components.INSTANCE.room(h.component(), room) + ";"));
        }
        return List.copyOf(out);
    }

    /** The room nodes a header imports from the components' dual: {@code RoomTabStripTail}. */
    public List<String> roomNodes(ComponentHeader h) { return h.rooms().stream().map(r -> Components.INSTANCE.room(h.component(), r)).toList(); }

    /**
     * The name a top-level component's binding is stamped under: {@code manage}, then the component's name -
     * {@code manageDialog}. One for each component a module implements, so several never meet.
     */
    public String manage(ComponentHeader h) { return "manage" + JsLiterals.INSTANCE.name(h.component().token()); }

    /**
     * The stamp as the module would begin: its imports, tree by tree, then a constant for each room; and, for a top-level
     * component, the page's style manager imported and bound to it - {@code const manageDialog =
     * styleManager.unit(ComponentDialog);} - the one way its implementation hands over its element at the root. A
     * component only ever a part has none: its owner hands it its reference.
     */
    public String stamp(ComponentHeader h, PageStyleManager page) {
        var js = JsLiterals.INSTANCE;
        var lines = new ArrayList<String>();
        lines.add("// Stamped by the server (RFC 0071, D19): " + h.component().token() + "'s header, made from its declarations - "
                + (h.composite() ? "a composite" : "a leaf") + (h.topLevel() ? ", top-level." : ", only ever a part."));
        List<DualRef.Ref> refs = refs(h);
        for (DualTree<?> tree : ReadDual.INSTANCE.trees()) {
            var names = new ArrayList<>(refs.stream().filter(r -> r.tree().equals(tree)).map(r -> r.name() + " as " + r.imported()).toList());
            if (tree instanceof Components) { names.addAll(fixed(h)); names.addAll(roomNodes(h)); }
            if (!names.isEmpty()) lines.add("import { " + String.join(", ", names) + " } from " + js.string(tree.address()) + ";");
        }
        if (h.topLevel()) lines.add("import { " + PageStyleManager.EXPORTED + " } from " + js.string(page.address()) + ";");
        for (Constant s : rooms(h)) lines.add(s.line());
        if (h.topLevel())
            lines.add("const " + manage(h) + " = " + PageStyleManager.EXPORTED + ".unit(" + DualRef.INSTANCE.of(h.component()).imported() + ");");
        return String.join("\n", lines) + "\n";
    }

    /**
     * The stamp of a module that implements several components - a branch's base, every leaf under it; the icons' one
     * class - their headers merged (D19): each tree's imports once, two headers naming one node; the page's style
     * manager's once; each room's constant once - two rooms of one name, the leaves' own, left out: each leaf's module names
     * its own and hands it to the base (23 Oct); a binding for each top-level component, each under its own name. One
     * header is stamped as it is.
     */
    public String stamp(List<ComponentHeader> headers, PageStyleManager page) {
        if (headers.isEmpty()) throw new IllegalArgumentException("a stamp is of one header at least");
        if (headers.size() == 1) return stamp(headers.getFirst(), page);
        var js = JsLiterals.INSTANCE;
        var lines = new ArrayList<String>();
        lines.add("// Stamped by the server (RFC 0071, D19): the headers of " + String.join(", ", headers.stream().map(h -> h.component().token()).toList())
                + ", merged - each made from its declarations.");
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
        var twice = new java.util.HashSet<String>();
        for (ComponentHeader h : headers) for (Constant s : rooms(h)) {
            String was = constants.putIfAbsent(s.name(), s.line());
            if (was != null && !was.equals(s.line())) twice.add(s.name());   // two leaves' rooms of one name: neither is the base's
        }
        twice.forEach(constants::remove);
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

    /**
     * The fixed types it imports (D30): its component's, then each part's, each once - every one an owner may make, and
     * places by - then those its parts' choices take, all the way down: a bar makes its chips' fixed objects, each with
     * its cross's (22 Oct).
     */
    public List<String> fixed(ComponentHeader h) {
        var out = new LinkedHashSet<String>();
        out.add(FixedTypes.INSTANCE.name(h.component()));
        for (ComponentHeader.HeaderSlot s : h.slots()) out.add(FixedTypes.INSTANCE.name(s.part()));
        for (Component<?> c : h.chosen()) out.add(FixedTypes.INSTANCE.name(c));
        return List.copyOf(out);
    }

    private static void add(LinkedHashMap<String, DualRef.Ref> out, DualRef.Ref r) { out.putIfAbsent(r.imported(), r); }
}
