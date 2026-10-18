package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Each style's flat mapping, held: a path for every element, each its own; each at the real class of what the element
 * wears; one real class a CSS implementation, whatever paths wear it; and a switch re-classing exactly the paths whose
 * class differs. The modules are written to target/mappings, one a style.
 */
class PathMappingTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();

    @Test
    void eachStyle_mapsEveryPath_atTheClassOfWhatItWears() throws IOException {
        Files.createDirectories(Path.of("target/mappings"));
        var mappings = new ArrayList<PathMapping>();
        for (DesignStyle<?> s : SPEC.selectable()) {
            PathMapping m = ReadPathMapping.INSTANCE.read(SPEC, s);
            assertEquals(SPEC.elements().size(), m.classes().size(), s.token() + ": a path for every element");
            var byClass = new HashMap<String, Class<?>>();
            for (ElementPath e : SPEC.elements()) {
                SpecRegistry.Worn w = SPEC.worn(s, e);
                String real = m.classes().get(e.path());
                assertEquals(ReadPathMapping.INSTANCE.realClass(w), real, s.token() + " " + e.path());
                Class<?> before = byClass.putIfAbsent(real, w.impl().getClass());
                assertTrue(before == null || before == w.impl().getClass(), real + " names two implementations");
            }
            Files.writeString(Path.of("target/mappings/" + s.token() + ".js"), m.script());
            mappings.add(m);
            System.out.println("[map] " + s.token() + ": " + m.classes().size() + " paths -> " + m.realClasses().size() + " real classes, module "
                    + m.script().length() + " characters");
        }
        Map<String, List<String>> switches = new HashMap<>();
        PathMapping first = mappings.getFirst();
        for (PathMapping to : mappings) {
            if (to == first) continue;
            List<String> changed = first.changedIn(to);
            for (String h : first.classes().keySet())
                assertEquals(changed.contains(h), !first.classes().get(h).equals(to.classes().get(h)), h);
            switches.put(to.style(), changed);
        }
        var said = new ArrayList<String>();
        switches.forEach((to, changed) -> said.add(to + "=" + changed.size()));
        System.out.println("[map] a switch from " + first.style() + " re-classes: " + String.join(", ", said));
    }

    /** The base layer is no style a page wears: every registered style but it is selectable, and it has no mapping of its own. */
    @Test
    void theBase_isNeverServedAlone() {
        assertEquals(SPEC.styles().subList(1, SPEC.styles().size()), SPEC.selectable());
        assertFalse(SPEC.selectable().contains(SPEC.base()));
        var refused = assertThrows(IllegalArgumentException.class, () -> ReadPathMapping.INSTANCE.read(SPEC, SPEC.base()));
        assertTrue(refused.getMessage().contains("stands alone nowhere"), refused.getMessage());
        assertThrows(IllegalArgumentException.class, () -> ReadSheet.INSTANCE.read(SPEC, SPEC.base()));
    }
}
