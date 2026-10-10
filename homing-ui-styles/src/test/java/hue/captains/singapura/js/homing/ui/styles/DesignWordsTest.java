package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.StateValue;
import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseStates;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The design words the house's states are answered by, against the design states the styles answer
 * today: each of core's design states but rest is the established word of exactly one value of the
 * house's tree of states - Enabled's false is {@code disabled}, Down's true {@code active} - so the
 * styles can come to answer the tree's values without a word of theirs changing. The values with no
 * established word are answered by the type's name and the value's.
 */
class DesignWordsTest {

    /** Every value but the rest, by its word: {@code disabled -> enabled(false)}. */
    private static Map<String, String> words() {
        var out = new LinkedHashMap<String, String>();
        for (StateType<?> t : HouseTaxonomy.INSTANCE.read().states().types()) {
            List<StateValue> values = t.values();
            for (StateValue v : values.subList(1, values.size())) out.put(t.word(v), t.name().value() + "(" + v + ")");
        }
        return out;
    }

    @Test
    void everyDesignStateButRest_isTheWordOfOneValueOfTheTree() {
        var expected = new LinkedHashMap<String, String>();
        expected.put("elevated", "elevation(elevated)");
        expected.put("sunken", "elevation(sunken)");
        expected.put("hover", "hovered(true)");
        expected.put("focus", "focused(true)");
        expected.put("disabled", "enabled(false)");
        expected.put("selected", "selected(true)");
        expected.put("current", "current(true)");
        expected.put("checked", "checked(true)");
        expected.put("pressed", "pressed(true)");
        expected.put("invalid", "invalid(true)");
        expected.put("expanded", "expanded(true)");
        expected.put("highlighted", "highlighted(true)");
        expected.put("candidate", "keyboard(candidate)");
        expected.put("lent", "keyboard(lent)");
        expected.put("held", "keyboard(held)");
        expected.put("active", "down(true)");
        var words = words();
        var actual = new LinkedHashMap<String, String>();
        for (State s : State.values())
            if (s != State.REST) actual.put(s.name().toLowerCase(Locale.ROOT), words.get(s.name().toLowerCase(Locale.ROOT)));
        assertEquals(expected, actual, "in the design's cascade order");
    }

    @Test
    void theValuesWithNoEstablishedWord_takeTheTypesNameAndTheirOwn() {
        var designStates = Arrays.stream(State.values()).map(s -> s.name().toLowerCase(Locale.ROOT)).collect(Collectors.toSet());
        assertEquals(List.of("read-only-true", "busy-true", "coverage-some", "coverage-all", "shown-true", "required-true",
                             "pointed-true", "empty-true"),
                words().keySet().stream().filter(w -> !designStates.contains(w)).toList());
        assertEquals("busy-true", HouseStates.Busy.INSTANCE.word(StateValue.TRUE));
    }
}
