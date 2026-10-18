package hue.captains.singapura.js.homing.design.content;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.TypeArguments;
import hue.captains.singapura.js.homing.design.spec.CssResolution;
import hue.captains.singapura.js.homing.design.spec.ElementPath;
import hue.captains.singapura.js.homing.design.spec.SpecRegistry;
import hue.captains.singapura.js.homing.design.spec.Trace;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styling.CastingCss;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The gauge, while today's stack stands (RFC 0067, D53): in every style, each element's content - what the typed spec
 * stack's resolver finds for the implementation the element wears - is exactly the CSS today's registry gives the
 * element's casting. It goes when today's stack does.
 */
class TodayTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final StyleManager TODAY = HouseStyles.INSTANCE.styling().manager();

    @Test
    void everyElementInEveryStyle_isGivenTodaysCss() {
        var wrong = new ArrayList<String>();
        int held = 0;
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                CastingStyle<?, ?, ?, ?> today = TODAY.resolve(s, e.casting()).css();
                String then = CastingCss.INSTANCE.read(today.getClass())
                        .orElseThrow(() -> new IllegalStateException(today.getClass().getName() + " has no CSS today"));
                switch (SPEC.content(s, e)) {
                    case CssResolution.Found f when f.text().equals(then) -> held++;
                    case CssResolution.Found f -> wrong.add(s.token() + " " + e.token() + ": " + f.resource() + " is not today's " + today.getClass().getName());
                    case CssResolution r -> wrong.add(s.token() + " " + e.token() + ": " + r);
                }
            }
        assertTrue(wrong.isEmpty(), wrong.size() + " differ:\n" + String.join("\n", wrong.subList(0, Math.min(40, wrong.size()))));
        System.out.println("[today] " + held + " elements' content, in every style, exactly today's CSS");
    }

    /**
     * Each element's CSS is of the style, the component and the role today's is - so what today's jars are held to of
     * their CSS (its rule, filled in for its casting; its colours imported both ways, D32) holds of the content here,
     * whose text and colours are today's.
     */
    @Test
    void everyElementInEveryStyle_wearsCssOfTodaysCasting() {
        var wrong = new ArrayList<String>();
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                Trace.CssStep now = SPEC.trace(s, e).end();
                List<Class<?>> then = TypeArguments.INSTANCE.of(TODAY.resolve(s, e.casting()).css().getClass(), CastingStyle.class);
                List<Class<?>> mine = List.of(now.layer().getClass(), now.casting().component().getClass(), now.casting().role().getClass());
                if (!mine.equals(then.subList(1, 4))) wrong.add(s.token() + " " + e.token() + ": " + names(mine) + ", today " + names(then.subList(1, 4)));
            }
        assertTrue(wrong.isEmpty(), wrong.size() + " differ:\n" + String.join("\n", wrong.subList(0, Math.min(40, wrong.size()))));
        System.out.println("[today] " + SPEC.styles().size() * SPEC.elements().size() + " elements' CSS of today's style, component and role");
    }

    private static List<String> names(List<Class<?>> types) { return types.stream().map(Class::getSimpleName).toList(); }
}
