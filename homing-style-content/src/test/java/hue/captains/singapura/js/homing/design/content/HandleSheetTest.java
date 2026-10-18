package hue.captains.singapura.js.homing.design.content;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.ElementPath;
import hue.captains.singapura.js.homing.design.spec.ReadHandleSheet;
import hue.captains.singapura.js.homing.design.spec.SpecRegistry;
import hue.captains.singapura.js.homing.design.spec.StyleClasses;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.styling.ReadCss;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Each style's sheet keyed by handles (RFC 0071): it reads as CSS; every handle is selected by exactly one of its rules,
 * the rule of the implementation the element wears in the style; no mark is left; no class names a style. Written to
 * target/handle-sheets, one a selectable style.
 */
class HandleSheetTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final Path OUT = Path.of("target/handle-sheets");

    @Test
    void eachStyle_selectsEveryHandleOnce_atWhatItWears() throws IOException {
        Files.createDirectories(OUT);
        try (Stream<Path> old = Files.list(OUT)) { for (Path p : old.toList()) Files.delete(p); }
        var problems = new ArrayList<String>();
        for (DesignStyle<?> s : SPEC.selectable()) {
            ReadHandleSheet.Sheet sheet = ReadHandleSheet.INSTANCE.read(SPEC, s);
            ReadCss.Read read = ReadCss.INSTANCE.read(sheet.css());
            read.problems().forEach(p -> problems.add(s.token() + ": " + p));
            Map<String, String> ruleOf = new HashMap<>();
            for (ReadHandleSheet.Rule r : sheet.rules())
                for (String h : r.handles()) {
                    String before = ruleOf.put(h, r.name());
                    if (before != null) problems.add(s.token() + ": " + h + " selected by " + before + " and " + r.name());
                }
            for (ElementPath e : SPEC.elements()) {
                String h = StyleClasses.INSTANCE.element(e);
                String wears = hue.captains.singapura.js.homing.design.spec.ReadHandles.INSTANCE.realClass(SPEC.worn(s, e));
                if (!wears.equals(ruleOf.get(h))) problems.add(s.token() + ": " + h + " selected by " + ruleOf.get(h) + ", and wears " + wears);
            }
            if (ruleOf.size() != SPEC.elements().size()) problems.add(s.token() + ": selects " + ruleOf.size() + " of " + SPEC.elements().size() + " handles");
            if (sheet.css().indexOf('$') >= 0) problems.add(s.token() + ": a mark is left");
            for (ReadCss.Item item : read.items())
                if (item instanceof ReadCss.Rule r && r.selector().contains(".styled-")) problems.add(s.token() + ": a selector names a style - " + r.selector().trim());
            Files.writeString(OUT.resolve(s.token() + ".css"), sheet.css());
            System.out.println("[handle-sheet] " + s.token() + ": " + sheet.rules().size() + " rules select " + ruleOf.size() + " handles, "
                    + sheet.css().length() + " characters");
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems.subList(0, Math.min(40, problems.size()))));
        try (Stream<Path> written = Files.list(OUT)) { assertEquals(SPEC.selectable().size(), written.count()); }
    }

    /** The classes on elements are the same in every style: a style is the sheet's, never an element's. */
    @Test
    void anElementsClasses_nameNoStyle() {
        List<String> named = SPEC.elements().stream().map(StyleClasses.INSTANCE::element).filter(c -> c.contains("styled-")).toList();
        assertEquals(List.of(), named);
    }
}
