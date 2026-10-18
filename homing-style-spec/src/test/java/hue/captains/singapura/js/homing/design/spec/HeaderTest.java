package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Every component's header, read off the taxonomy (RFC 0071, D19): its slots the stack's own (the slots a declaration
 * points), its states exactly those it declares, a leaf with no slot and a composite with some; and its stamp binding
 * each name once, naming no class.
 */
class HeaderTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();

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
            for (ComponentHeader.HeaderState s : h.states())
                if (s.allowed().size() < 2 || s.words().size() != s.allowed().size() - 1) problems.add(c.token() + ": " + s.token() + " carries too little");
            slots += h.slots().size();
            states += h.states().size();
            events += h.events().size();
            browsers += (int) h.states().stream().filter(s -> s.browser().isPresent()).count();
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
        System.out.println("[header] " + SPEC.taxonomy().components().size() + " headers: " + slots + " slots, " + states + " state types ("
                + browsers + " the browser's), " + events + " events");
    }

    @Test
    void everyStamp_bindsEachNameOnce_andNamesNoClass() {
        var problems = new ArrayList<String>();
        for (ComponentHeader h : ReadHeader.INSTANCE.readAll(SPEC.taxonomy(), SPEC.top())) {
            var names = new HashSet<String>();
            for (HeaderStamp.Binding b : HeaderStamp.INSTANCE.bindings(h))
                if (!names.add(b.name())) problems.add(h.component().token() + ": " + b.name() + " bound twice");
            String stamp = HeaderStamp.INSTANCE.stamp(h);
            if (stamp.contains("styled-")) problems.add(h.component().token() + ": its stamp names a class");
            if (!stamp.contains("styleManager.header(import.meta.url)")) problems.add(h.component().token() + ": its stamp asks for no header");
        }
        assertEquals(List.of(), problems);
    }
}
