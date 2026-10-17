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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The typed spec stack's CSS, copied, held to today's: in every style every element wears the very CSS - and the very
 * colours - today's registry gives its casting; every look with CSS of its own has its file at its canonical name, its
 * marks in form; and no file is of no look.
 */
class HouseSpecCssTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final StyleManager TODAY = HouseStyles.INSTANCE.styling().manager();
    private static final CastingCss CSS = CastingCss.INSTANCE;

    @Test
    void everyElementInEveryStyle_wearsTodaysCss_andTodaysColours() {
        var wrong = new ArrayList<String>();
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                CastingStylePerRole<?, ?, ?, ?> now = SPEC.wears(s, e);
                CastingStyle<?, ?, ?, ?> today = TODAY.resolve(s, e.casting()).css();
                String at = s.token() + " " + e.token();
                String mine = CSS.read(now.getClass()).orElse(null);
                if (mine == null) { wrong.add(at + ": " + CSS.resource(now.getClass()) + " is missing"); continue; }
                if (!mine.equals(CSS.read(today.getClass()).orElseThrow())) wrong.add(at + ": its CSS is not " + today.getClass().getName() + "'s");
                if (!colours(now).equals(coloursToday(today))) wrong.add(at + ": its colours " + colours(now) + ", today " + coloursToday(today));
            }
        assertTrue(wrong.isEmpty(), wrong.size() + " differ:\n" + String.join("\n", wrong.subList(0, Math.min(40, wrong.size()))));
    }

    @Test
    void everyFile_isOfALookWithCssOfItsOwn_itsMarksInForm() throws IOException {
        Set<String> files = new LinkedHashSet<>();
        Path root = Path.of("src/main/resources");
        try (Stream<Path> walk = Files.walk(root)) {
            walk.filter(p -> p.toString().endsWith(".css")).forEach(p -> files.add(root.relativize(p).toString().replace('\\', '/')));
        }
        Set<String> looks = new LinkedHashSet<>();
        var problems = new ArrayList<String>();
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                CastingStylePerRole<?, ?, ?, ?> now = SPEC.wears(s, e);
                String file = CSS.resource(now.getClass());
                if (looks.add(file)) {
                    var marked = CSS.marks(now.getClass().getSimpleName() + ".css", CSS.read(now.getClass()).orElse(""));
                    if (!marked.passes()) problems.add(file + ": " + marked.problems());
                }
            }
        var strays = new ArrayList<>(files);
        strays.removeAll(looks);
        System.out.println("[spec] " + files.size() + " CSS files, " + looks.size() + " worn by some element in some style");
        assertTrue(problems.isEmpty(), String.join("\n", problems));
        assertEquals(List.of(), strays, "files of no look worn");
    }

    private static List<String> colours(CastingStylePerRole<?, ?, ?, ?> look) {
        if (!(look.style() instanceof StylingRes.CssContent<?, ?, ?, ?> c)) throw new IllegalStateException(look + " has no CSS of its own");
        return c.colours().stream().map(ColorLeaf::token).toList();
    }

    private static List<String> coloursToday(CastingStyle<?, ?, ?, ?> look) {
        if (!(look.style() instanceof hue.captains.singapura.js.homing.design.styles.StylingRes.CssContent<?, ?> c))
            throw new IllegalStateException(look + " has no CSS of its own");
        return c.colours().stream().map(ColorLeaf::token).toList();
    }
}
