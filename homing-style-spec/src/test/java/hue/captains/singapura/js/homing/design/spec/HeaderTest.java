package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.js.homing.design.spec.dual.DualTree;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
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

    /** Every object a module makes is made by one of the kit's constructors, each one the module imports; none made by hand. */
    private static void madeByTheKit(String who, String script, List<String> problems) {
        String kitImport = script.lines().filter(l -> l.startsWith("import ") && l.contains(DualKit.INSTANCE.address())).findFirst().orElse("");
        for (Matcher m = MADE.matcher(script); m.find(); ) {
            String c = m.group(1);
            if (!DualKit.INSTANCE.classes().contains(c)) problems.add(who + ": makes a " + c + ", no class of the kit's");
            else if (!kitImport.matches(".*[{ ,]" + c + "[ ,}].*")) problems.add(who + ": makes a " + c + " it does not import");
        }
        if (script.contains("Object.freeze(")) problems.add(who + ": an object made by hand");
    }

    private static Map<DualTree<?>, TreeDual> duals() {
        var out = new HashMap<DualTree<?>, TreeDual>();
        for (DualTree<?> t : ReadDual.INSTANCE.trees()) out.put(t, ReadDual.INSTANCE.read(t, SPEC.taxonomy()));
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
        int slots = 0, states = 0, events = 0, browsers = 0;
        for (ComponentHeader h : ReadHeader.INSTANCE.readAll(SPEC.taxonomy(), SPEC.top())) {
            Component<?> c = h.component();
            List<String> mine = h.slots().stream().map(ComponentHeader.HeaderSlot::token).toList();
            List<String> stack = Slots.INSTANCE.of(SPEC.taxonomy(), c).stream().map(s -> s.slot().token()).toList();
            if (!mine.equals(stack)) problems.add(c.token() + ": slots " + mine + ", the stack's " + stack);
            if (h.composite() == h.slots().isEmpty()) problems.add(c.token() + ": " + (h.composite() ? "a composite with no slot" : "a leaf with slots"));
            if (h.topLevel() != SPEC.top().contains(c)) problems.add(c.token() + ": top-level wrongly said");
            if (!h.states().stream().map(ComponentHeader.HeaderState::type).toList().equals(c.states()))
                problems.add(c.token() + ": states other than it declares");
            slots += h.slots().size();
            states += h.states().size();
            events += h.events().size();
            browsers += (int) h.states().stream().filter(s -> s.browser().isPresent()).count();
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
        System.out.println("[header] " + SPEC.taxonomy().components().size() + " headers: " + slots + " slots, " + states + " state types ("
                + browsers + " the browser's), " + events + " events");
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
            for (HeaderStamp.SlotConstant s : HeaderStamp.INSTANCE.slots(h))
                if (!names.add(s.name())) problems.add(h.component().token() + ": " + s.name() + " stamped twice");
            String stamp = HeaderStamp.INSTANCE.stamp(h);
            if (stamp.contains("$")) problems.add(h.component().token() + ": an address with a nested class's name");
            madeByTheKit(h.component().token(), stamp, problems);
            for (String line : stamp.lines().toList())
                if (!line.startsWith("import ") && !line.startsWith("//") && line.contains("\"")) problems.add(h.component().token() + ": writes a string - " + line.trim());
            if (stamp.contains("styled-")) problems.add(h.component().token() + ": names a class");
        }
        assertEquals(List.of(), problems);
        System.out.println("[header] " + imports + " imports over every header, each in its dual");
    }

    /** The kit, every dual and every header written to target/js, each at its address's class, for a JS runtime to load. */
    @Test
    void everyModule_isWrittenOut_forAJsRuntimeToLoad() throws java.io.IOException {
        var out = java.nio.file.Path.of("target/js");
        java.nio.file.Files.createDirectories(out);
        java.nio.file.Files.writeString(out.resolve(DualKit.class.getCanonicalName() + ".js"), DualKit.INSTANCE.script());
        for (TreeDual d : duals().values())
            java.nio.file.Files.writeString(out.resolve(d.tree().getClass().getCanonicalName() + ".js"), DualScript.INSTANCE.script(d));
        int headers = 0;
        for (ComponentHeader h : ReadHeader.INSTANCE.readAll(SPEC.taxonomy(), SPEC.top())) {
            java.nio.file.Files.writeString(out.resolve("header." + h.component().token() + ".js"), HeaderStamp.INSTANCE.stamp(h));
            headers++;
        }
        System.out.println("[header] the kit, " + ReadDual.INSTANCE.trees().size() + " duals and " + headers + " headers written to " + out);
    }
}
