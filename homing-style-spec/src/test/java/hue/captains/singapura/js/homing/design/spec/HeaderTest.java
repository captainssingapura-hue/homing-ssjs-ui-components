package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.js.homing.design.spec.dual.FixedValuesKit;
import hue.captains.singapura.js.homing.design.spec.dual.DualTree;
import hue.captains.singapura.js.homing.design.spec.dual.Registration;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.spec.page.PageStyleManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The trees' JS duals and every component's header (RFC 0071): each dual made the one way, its names unique, each node
 * after its parent, keeping exactly the used and what is above them; every header read off the taxonomy, its imports a
 * pure function of its declarations, each resolving in its dual - so the duals suffice for every component - and its
 * header writing no string but a module's address.
 */
class HeaderTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final Pattern MADE = Pattern.compile("new ([A-Za-z]+)\\(");
    /** Where the test says the page's style manager is. */
    private static final PageStyleManager PAGE = new PageStyleManager("/module?class=hue.captains.singapura.js.homing.design.spec.page.PageStyles");

    /**
     * Every object a module makes is made by one of the kit's constructors - or the values' kit's - each one the module
     * imports from its kit; none made by hand.
     */
    private static void madeByTheKit(String who, String script, List<String> problems) {
        String kitImport = script.lines().filter(l -> l.startsWith("import ") && l.contains(DualKit.INSTANCE.address())).findFirst().orElse("");
        String valuesImport = script.lines().filter(l -> l.startsWith("import ") && l.contains(FixedValuesKit.INSTANCE.address())).findFirst().orElse("");
        for (Matcher m = MADE.matcher(script); m.find(); ) {
            String c = m.group(1);
            boolean kit = DualKit.INSTANCE.classes().contains(c), values = FixedValuesKit.INSTANCE.classes().contains(c);
            if (!kit && !values) problems.add(who + ": makes a " + c + ", no class of the kit's");
            else if (!(kit ? kitImport : valuesImport).matches(".*[{ ,]" + c + "[ ,}].*")) problems.add(who + ": makes a " + c + " it does not import");
        }
        if (script.contains("Object.freeze(")) problems.add(who + ": an object made by hand");
    }

    private static Map<DualTree<?>, TreeDual> duals() {
        var out = new HashMap<DualTree<?>, TreeDual>();
        for (DualTree<?> t : ReadDual.INSTANCE.trees()) out.put(t, ReadDual.INSTANCE.read(t, new Registration(SPEC.taxonomy(), SPEC.top(), SPEC.selectable())));
        return out;
    }

    @Test
    void everyTree_hasItsDual_madeTheOneWay() {
        var problems = new ArrayList<String>();
        var said = new ArrayList<String>();
        for (TreeDual d : duals().values()) {
            var seen = new HashSet<String>();
            for (TreeDual.Node n : d.nodes()) {
                if (n.parent().isPresent() && !seen.contains(n.parent().get())) problems.add(d.tree().name() + ": " + n.name() + " before its parent");
                if (!seen.add(n.name())) problems.add(d.tree().name() + ": " + n.name() + " twice");
            }
            String script = DualScript.INSTANCE.script(d);
            madeByTheKit(d.tree().name(), script, problems);
            if (!script.contains("export { ")) problems.add(d.tree().name() + ": exports nothing");
            said.add(d.tree().name() + " " + d.nodes().size() + " of " + d.inTree());
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
        System.out.println("[dual] kept of each tree, the used and what is above them: " + String.join(", ", said));
    }

    @Test
    void everyComponent_hasItsHeader_readOffTheTaxonomy() {
        var problems = new ArrayList<String>();
        int slots = 0, states = 0, browsers = 0;
        for (ComponentHeader h : ReadHeader.INSTANCE.readAll(SPEC.taxonomy(), SPEC.top())) {
            Component<?> c = h.component();
            List<String> mine = h.slots().stream().map(ComponentHeader.HeaderSlot::token).toList();
            List<String> stack = Slots.INSTANCE.of(SPEC.taxonomy(), c).stream().map(s -> s.slot().token()).toList();
            if (!mine.equals(stack)) problems.add(c.token() + ": slots " + mine + ", the stack's " + stack);
            if (h.composite() == h.slots().isEmpty()) problems.add(c.token() + ": " + (h.composite() ? "a composite with no slot" : "a leaf with slots"));
            if (h.topLevel() != (SPEC.top().contains(c) && !(c instanceof hue.captains.singapura.js.homing.component.taxonomy.PartOnly)))
                problems.add(c.token() + ": top-level wrongly said");   // listed, and not only ever a part (23 Oct)
            if (!h.states().stream().map(ComponentHeader.HeaderState::type).toList().equals(c.states()))
                problems.add(c.token() + ": states other than it declares");
            slots += h.slots().size();
            states += h.states().size();
            browsers += (int) h.states().stream().filter(s -> s.browser().isPresent()).count();
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
        System.out.println("[header] " + SPEC.taxonomy().components().size() + " headers: " + slots + " slots, " + states + " state types ("
                + browsers + " the browser's)");
    }

    /** Every name a header imports is in its dual: the duals suffice for every component, and its imports are its declarations'. */
    @Test
    void everyHeader_importsOnlyWhatItsDualsHave_andWritesNoString() {
        Map<DualTree<?>, TreeDual> duals = duals();
        var problems = new ArrayList<String>();
        int imports = 0;
        for (ComponentHeader h : ReadHeader.INSTANCE.readAll(SPEC.taxonomy(), SPEC.top())) {
            var names = new HashSet<String>();
            for (DualRef.Ref r : HeaderStamp.INSTANCE.refs(h)) {
                if (!duals.get(r.tree()).has(r.name())) problems.add(h.component().token() + ": imports " + r.name() + ", which the " + r.tree().name() + " dual has not");
                if (!names.add(r.imported())) problems.add(h.component().token() + ": " + r.imported() + " imported twice");
                imports++;
            }
            for (HeaderStamp.Constant s : HeaderStamp.INSTANCE.rooms(h))
                if (!names.add(s.name())) problems.add(h.component().token() + ": " + s.name() + " stamped twice");
            String stamp = HeaderStamp.INSTANCE.stamp(h, PAGE);
            String binding = "const " + HeaderStamp.INSTANCE.manage(h) + " = " + PageStyleManager.EXPORTED + ".unit("
                    + DualRef.INSTANCE.of(h.component()).imported() + ");";
            String pageImport = "import { " + PageStyleManager.EXPORTED + " } from \"" + PAGE.address() + "\";";
            long mentions = stamp.lines().filter(l -> l.contains(PageStyleManager.EXPORTED)).count();
            if (h.topLevel() && (mentions != 2 || !stamp.lines().toList().contains(binding) || !stamp.lines().toList().contains(pageImport)))
                problems.add(h.component().token() + ": top-level, and not bound once to the page's style manager");
            if (!h.topLevel() && mentions != 0) problems.add(h.component().token() + ": only ever a part, and bound to the page's style manager");
            if (h.topLevel() && !names.add(HeaderStamp.INSTANCE.manage(h))) problems.add(h.component().token() + ": its binding's name stamped twice");
            if (stamp.contains("$")) problems.add(h.component().token() + ": an address with a nested class's name");
            madeByTheKit(h.component().token(), stamp, problems);
            for (String line : stamp.lines().toList())
                if (!line.startsWith("import ") && !line.startsWith("//") && line.contains("\"")) problems.add(h.component().token() + ": writes a string - " + line.trim());
            if (stamp.contains("styled-")) problems.add(h.component().token() + ": names a class");
        }
        assertEquals(List.of(), problems);
        System.out.println("[header] " + imports + " imports over every header, each in its dual");
    }

    /**
     * A module implementing several components - every leaf under a branch, as a branch's base is - is stamped with their
     * headers merged: each name imported once, the page's style manager once, a binding for each top-level one, no two
     * slots under one name - over every branch of the house with more than one component.
     */
    @Test
    void everyBranchsHeaders_merge() {
        var byBranch = new java.util.LinkedHashMap<Object, List<ComponentHeader>>();
        for (ComponentHeader h : ReadHeader.INSTANCE.readAll(SPEC.taxonomy(), SPEC.top()))
            byBranch.computeIfAbsent(h.component().parent(), b -> new ArrayList<>()).add(h);
        var problems = new ArrayList<String>();
        int merged = 0;
        for (List<ComponentHeader> hs : byBranch.values()) {
            if (hs.size() < 2) continue;
            String who = hs.getFirst().component().parent().token();
            String stamp;
            try { stamp = HeaderStamp.INSTANCE.stamp(hs, PAGE); }
            catch (IllegalArgumentException e) { problems.add(who + ": " + e.getMessage()); continue; }
            var names = new HashSet<String>();
            for (String line : stamp.lines().toList()) {
                if (!line.startsWith("import {")) continue;
                for (String n : line.substring("import { ".length(), line.indexOf(" }")).split(", ")) {
                    String as = n.contains(" as ") ? n.substring(n.indexOf(" as ") + 4) : n;
                    if (!names.add(as)) problems.add(who + ": " + as + " imported twice");
                }
            }
            long pageImports = stamp.lines().filter(l -> l.startsWith("import { " + PageStyleManager.EXPORTED + " }")).count();
            if (pageImports != (hs.stream().anyMatch(ComponentHeader::topLevel) ? 1 : 0)) problems.add(who + ": the page's style manager imported " + pageImports + " times");
            for (ComponentHeader h : hs) if (h.topLevel() && !stamp.contains("const " + HeaderStamp.INSTANCE.manage(h) + " = ")) problems.add(who + ": no binding for " + h.component().token());
            madeByTheKit(who, stamp, problems);
            merged++;
        }
        assertEquals(List.of(), problems);
        System.out.println("[header] " + merged + " branches' headers merged, each name once");
    }

    /** The kit, every dual and every header written to target/js, each at its address's class, for a JS runtime to load. */
    @Test
    void everyModule_isWrittenOut_forAJsRuntimeToLoad() throws java.io.IOException {
        var out = java.nio.file.Path.of("target/js");
        java.nio.file.Files.createDirectories(out);
        java.nio.file.Files.writeString(out.resolve(DualKit.class.getCanonicalName() + ".js"), DualKit.INSTANCE.script());
        java.nio.file.Files.writeString(out.resolve(FixedValuesKit.class.getCanonicalName() + ".js"), FixedValuesKit.INSTANCE.script());
        for (TreeDual d : duals().values())
            java.nio.file.Files.writeString(out.resolve(d.tree().getClass().getCanonicalName() + ".js"), DualScript.INSTANCE.script(d));
        int headers = 0;
        for (ComponentHeader h : ReadHeader.INSTANCE.readAll(SPEC.taxonomy(), SPEC.top())) {
            java.nio.file.Files.writeString(out.resolve("header." + h.component().token() + ".js"), HeaderStamp.INSTANCE.stamp(h, PAGE));
            headers++;
        }
        System.out.println("[header] the kit, " + ReadDual.INSTANCE.trees().size() + " duals and " + headers + " headers written to " + out);
    }
}
