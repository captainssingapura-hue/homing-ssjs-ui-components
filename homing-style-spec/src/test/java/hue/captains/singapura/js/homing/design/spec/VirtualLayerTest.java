package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styling.CastingCss;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The virtual layer holds no CSS (RFC 0067, D53): no file in the module, and no content yet on the classpath for any
 * implementation that has CSS of its own - its content is a resource at its canonical name, from a jar of content to
 * come. What it holds of the palette is typed: in every style each element's implementation with CSS draws with exactly
 * the colours today's registry gives the element's casting.
 */
class VirtualLayerTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final StyleManager TODAY = HouseStyles.INSTANCE.styling().manager();

    @Test
    void theModuleHoldsNoCssFile() throws IOException {
        Path resources = Path.of("src/main/resources");
        if (!Files.exists(resources)) return;
        try (Stream<Path> walk = Files.walk(resources)) {
            assertEquals(List.of(), walk.filter(p -> p.toString().endsWith(".css")).toList(), "the virtual layer holds no CSS");
        }
    }

    @Test
    void noImplementation_hasContentYet() {
        var found = new ArrayList<String>();
        int looked = 0;
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                CssImplementation<?, ?, ?, ?> now = SPEC.wears(s, e);
                looked++;
                String file = CastingCss.INSTANCE.resource(now.getClass());
                if (now.getClass().getClassLoader().getResource(file) != null) found.add(file);
            }
        System.out.println("[virtual] " + looked + " elements' implementations with CSS of their own; content found for " + found.size());
        assertEquals(List.of(), found.stream().distinct().toList(), "no content yet: a jar of content gives it");
    }

    @Test
    void everyElementInEveryStyle_drawsWithTodaysColours() {
        var wrong = new ArrayList<String>();
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                List<String> now = SPEC.wears(s, e).colours().stream().map(ColorLeaf::token).toList();
                CastingStyle<?, ?, ?, ?> today = TODAY.resolve(s, e.casting()).css();
                if (!(today.style() instanceof hue.captains.singapura.js.homing.design.styles.StylingRes.CssContent<?, ?> c))
                    throw new IllegalStateException(today + " has no CSS of its own");
                List<String> then = c.colours().stream().map(ColorLeaf::token).toList();
                if (!now.equals(then)) wrong.add(s.token() + " " + e.token() + ": " + now + ", today " + then);
            }
        assertTrue(wrong.isEmpty(), wrong.size() + " differ:\n" + String.join("\n", wrong.subList(0, Math.min(40, wrong.size()))));
    }
}
