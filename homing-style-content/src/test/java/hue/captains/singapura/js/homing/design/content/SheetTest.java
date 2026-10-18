package hue.captains.singapura.js.homing.design.content;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.PathMapping;
import hue.captains.singapura.js.homing.design.spec.ReadPathMapping;
import hue.captains.singapura.js.homing.design.spec.ReadSheet;
import hue.captains.singapura.js.homing.design.spec.SpecRegistry;
import hue.captains.singapura.js.homing.design.spec.StyleSheet;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.styling.ReadCss;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Each style's complete sheet as served (RFC 0067, D53), with its path mapping: the sheet reads as CSS; it rules
 * exactly the classes its mapping puts on elements, each once; no mark is left; no keyframes is named twice. Both are
 * written to target/sheets, the sheet and the mapping a page loads for the style.
 */
class SheetTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final StyleManager TODAY = HouseStyles.INSTANCE.styling().manager();
    private static final Path OUT = Path.of("target/sheets");

    @Test
    void eachStyle_isServedWhole_withItsMapping() throws IOException {
        Files.createDirectories(OUT);
        try (Stream<Path> old = Files.list(OUT)) {
            for (Path p : old.toList()) Files.delete(p);
        }
        var problems = new ArrayList<String>();
        for (DesignStyle<?> s : SPEC.selectable()) {
            StyleSheet sheet = ReadSheet.INSTANCE.read(SPEC, s);
            PathMapping mapping = ReadPathMapping.INSTANCE.read(SPEC, s);
            ReadCss.Read read = ReadCss.INSTANCE.read(sheet.css());
            read.problems().forEach(p -> problems.add(s.token() + ": " + p));
            var ruled = new ArrayList<String>();
            var keyframes = new HashSet<String>();
            for (ReadCss.Item item : read.items())
                switch (item) {
                    case ReadCss.Rule r -> ruled.add(r.selector().trim());
                    case ReadCss.AtRule a when a.prelude().trim().startsWith("keyframes ") -> {
                        if (!keyframes.add(a.prelude().trim())) problems.add(s.token() + ": @" + a.prelude().trim() + " twice");
                    }
                    case ReadCss.AtRule a -> { }
                    case ReadCss.Declaration d -> problems.add(s.token() + ": " + d.property() + " declared outside any rule");
                }
            Set<String> classes = new LinkedHashSet<>(mapping.realClasses());
            if (ruled.size() != new HashSet<>(ruled).size()) problems.add(s.token() + ": a class ruled twice");
            Set<String> expected = new LinkedHashSet<>();
            for (String c : classes) expected.add("." + c);
            if (!new HashSet<>(ruled).equals(expected))
                problems.add(s.token() + ": rules " + ruled.size() + " classes, its mapping puts " + classes.size() + " on elements");
            if (sheet.css().indexOf('$') >= 0) problems.add(s.token() + ": a mark is left at " + sheet.css().indexOf('$'));
            Files.writeString(OUT.resolve(s.token() + ".css"), sheet.css());
            Files.writeString(OUT.resolve(s.token() + ".js"), mapping.script());
            System.out.println("[sheet] " + s.token() + ": " + sheet.rules().size() + " rules for " + mapping.classes().size() + " paths, "
                    + sheet.css().length() + " characters (today's sheet " + TODAY.sheet(s).length() + ")");
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems.subList(0, Math.min(40, problems.size()))));
        try (Stream<Path> written = Files.list(OUT)) {
            assertEquals(SPEC.selectable().size() * 2L, written.count(), "a sheet and a mapping for each selectable style, none for the base");
        }
    }

    /** Every element's rule, as served, is its content with the marks filled in for the class it wears. */
    @Test
    void everyPath_findsItsRule() {
        for (DesignStyle<?> s : SPEC.selectable()) {
            StyleSheet sheet = ReadSheet.INSTANCE.read(SPEC, s);
            List<String> without = ReadPathMapping.INSTANCE.read(SPEC, s).classes().entrySet().stream()
                    .filter(e -> sheet.rule(e.getValue()).isEmpty()).map(e -> e.getKey() + " -> " + e.getValue()).toList();
            assertEquals(List.of(), without, s.token());
        }
    }
}
