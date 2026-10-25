package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.taxonomy.IconLeaf;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house in the typed spec stack, held to today's registry: it reads, every rule held; it has today's styles; and in
 * every style every element - each top-level component, and every slot down from it - wears the look that stands for the
 * look with CSS today's registry gives the element's casting (copies.tsv: whose CSS each new look is to copy).
 */
class HouseSpecTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final StyleManager TODAY = HouseStyles.INSTANCE.styling().manager();

    @Test
    void readsAndHoldsEveryRule_withTodaysStyles() {
        assertEquals(TODAY.styles().stream().map(DesignStyle::token).toList(), SPEC.styles().stream().map(DesignStyle::token).toList());
        assertEquals(TODAY.base(), SPEC.base());
    }

    @Test
    void everyElementInEveryStyle_wearsWhatTodayGivesItsCasting() throws IOException {
        Map<String, String> copies = copies();
        var wrong = new ArrayList<String>();
        int checked = 0;
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                String now = SPEC.wears(s, e).getClass().getName();
                String copied = copies.get(now);
                assertNotNull(copied, now + " is a look with CSS, and copies none");
                String today = TODAY.resolve(s, e.casting()).css().getClass().getName();
                // D56: a style's own look of an icon differed from base's by its glyph alone, which is its icon palette's
                // now (HouseIconPalettesTest) - so today's base look is the one an icon's stands for, in every style
                if (e.casting().component() instanceof IconLeaf) today = TODAY.resolve(SPEC.base(), e.casting()).css().getClass().getName();
                if (!copied.equals(today)) wrong.add(s.token() + " " + e.token() + ": " + now + " stands for " + copied + ", today " + today);
                checked++;
            }
        System.out.println("[spec] " + SPEC.elements().size() + " elements in " + SPEC.styles().size() + " styles: " + checked + " checked, " + wrong.size() + " differ");
        assertTrue(wrong.isEmpty(), String.join("\n", wrong.subList(0, Math.min(40, wrong.size()))));
    }

    private static Map<String, String> copies() throws IOException {
        try (InputStream in = HouseSpecTest.class.getResourceAsStream("copies.tsv")) {
            var out = new HashMap<String, String>();
            for (String line : new String(in.readAllBytes(), StandardCharsets.UTF_8).split("\n")) {
                if (line.isBlank()) continue;
                String[] kv = line.strip().split("\t");
                out.put(kv[0], kv[1]);
            }
            return out;
        }
    }

    @Test
    void summary() {
        var lines = new ArrayList<String>();
        for (DesignStyle<?> s : SPEC.styles()) lines.add(s.token() + "=" + SPEC.assignments().get(s).size());
        System.out.println("[spec] top-level components " + SPEC.top().size() + "; assignments per layer: " + String.join(", ", lines));
        assertEquals(List.copyOf(SPEC.top()), SPEC.top());
    }
}
