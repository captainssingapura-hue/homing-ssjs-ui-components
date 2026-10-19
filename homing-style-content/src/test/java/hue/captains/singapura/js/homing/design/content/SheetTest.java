package hue.captains.singapura.js.homing.design.content;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.ReadSheet;
import hue.captains.singapura.js.homing.design.spec.SpecRegistry;
import hue.captains.singapura.js.homing.design.spec.StyleClasses;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Each style's sheet as served (RFC 0071, RFC 0067 D54): it reads as CSS; it selects every casting's class once, by the
 * rule of the implementation the casting wears; no mark is left; no keyframes is named twice; and the classes it selects
 * by name no style - a style is the sheet's, never an element's. Each is written to {@code target/sheets}.
 */
class SheetTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final StyleManager TODAY = HouseStyles.INSTANCE.styling().manager();
    private static final Path OUT = Path.of("target/sheets");

    @Test
    void eachStyle_selectsEveryCastingOnce_atWhatItWears() throws IOException {
        Files.createDirectories(OUT);
        try (Stream<Path> old = Files.list(OUT)) { for (Path p : old.toList()) Files.delete(p); }
        var problems = new ArrayList<String>();
        for (DesignStyle<?> s : SPEC.selectable()) {
            StyleSheet sheet = ReadSheet.INSTANCE.read(SPEC, s);
            ReadCss.Read read = ReadCss.INSTANCE.read(sheet.css());
            read.problems().forEach(p -> problems.add(s.token() + ": " + p));
            Map<String, StyleSheet.Rule> ruleOf = new HashMap<>();
            for (StyleSheet.Rule r : sheet.rules())
                for (String c : r.castings()) {
                    StyleSheet.Rule before = ruleOf.put(c, r);
                    if (before != null) problems.add(s.token() + ": " + c + " selected by " + before.name() + " and " + r.name());
                }
            for (Casting k : SPEC.castings()) {
                String c = StyleClasses.INSTANCE.casting(k);
                StyleSheet.Rule r = ruleOf.get(c);
                if (r == null) problems.add(s.token() + ": " + c + " selected by no rule");
                else if (r.end().impl().getClass() != SPEC.wears(s, k).getClass())
                    problems.add(s.token() + ": " + c + " selected by " + r.name() + ", and wears " + SPEC.wears(s, k).getClass().getName());
            }
            if (ruleOf.size() != SPEC.castings().size()) problems.add(s.token() + ": selects " + ruleOf.size() + " of " + SPEC.castings().size() + " castings");
            if (sheet.css().indexOf('$') >= 0) problems.add(s.token() + ": a mark is left at " + sheet.css().indexOf('$'));
            var keyframes = new HashSet<String>();
            for (ReadCss.Item item : read.items())
                switch (item) {
                    case ReadCss.Rule r when r.selector().contains(".styled-") -> problems.add(s.token() + ": a selector names a style - " + r.selector().trim());
                    case ReadCss.Rule r -> { }
                    case ReadCss.AtRule a when a.prelude().trim().startsWith("keyframes ") -> {
                        if (!keyframes.add(a.prelude().trim())) problems.add(s.token() + ": @" + a.prelude().trim() + " twice");
                    }
                    case ReadCss.AtRule a -> { }
                    case ReadCss.Declaration d -> problems.add(s.token() + ": " + d.property() + " declared outside any rule");
                }
            Files.writeString(OUT.resolve(s.token() + ".css"), sheet.css());
            System.out.println("[sheet] " + s.token() + ": " + sheet.rules().size() + " rules select " + ruleOf.size() + " castings, "
                    + sheet.css().length() + " characters (today's sheet " + TODAY.sheet(s).length() + ")");
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems.subList(0, Math.min(40, problems.size()))));
        try (Stream<Path> written = Files.list(OUT)) {
            assertEquals(SPEC.selectable().size(), written.count(), "a sheet for each selectable style, none for the base");
        }
    }

    /** A casting's class is the same in every style, and every element's is its casting's: an owner and a slot are the layout's. */
    @Test
    void anElementsClass_isItsCastings_andNamesNoStyle() {
        var castings = SPEC.castings().stream().map(StyleClasses.INSTANCE::casting).toList();
        assertEquals(List.of(), castings.stream().filter(c -> c.contains("styled-") || !c.startsWith("c-")).toList());
        assertEquals(new HashSet<>(castings), new HashSet<>(SPEC.elements().stream().map(StyleClasses.INSTANCE::element).toList()));
        System.out.println("[sheet] " + SPEC.elements().size() + " elements wear " + castings.size() + " classes, one for each casting");
    }
}
