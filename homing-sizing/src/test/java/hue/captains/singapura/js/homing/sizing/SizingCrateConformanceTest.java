package hue.captains.singapura.js.homing.sizing;

import hue.captains.singapura.js.homing.conformance.rules.CrateDependencyRule;
import hue.captains.singapura.js.homing.conformance.rules.DefaultJsRulePolicy;
import hue.captains.singapura.js.homing.conformance.rules.OrphanCheck;
import hue.captains.singapura.js.homing.conformance.rules.ServedModule;
import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.core.util.ResourceReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The crate serves its modules, imports nothing it does not require, keeps each lane's discipline, and its styles paint nothing. */
class SizingCrateConformanceTest {

    @Test
    void everyServedModuleIsCrated() {
        assertEquals(List.of(), OrphanCheck.check(SizingCrate.INSTANCE));
    }

    @Test
    void importsRespectCrateBoundaries() {
        assertEquals(List.of(), CrateDependencyRule.check(SizingCrate.INSTANCE));
    }

    @Test
    void everyServedModuleKeepsItsLanesDiscipline() {
        int checked = 0;
        for (var entry : SizingCrate.INSTANCE.entries()) {
            if (!(entry.declaredType() instanceof StandardJsModuleType type) || type == StandardJsModuleType.GENERATED_CSS) continue;
            String m = entry.moduleClass();
            String src = String.join("\n", ResourceReader.INSTANCE.getStringsFromResource("homing/js/" + m.replace('.', '/') + ".js"));
            var findings = DefaultJsRulePolicy.INSTANCE.rulesFor(type).checkAll(ServedModule.of(m, type, src));
            assertEquals(List.of(), findings, () -> m + ": " + findings.stream().map(f -> f.rule().value() + "@" + f.line() + ": " + f.message()).toList());
            checked++;
        }
        assertEquals(2, checked, "the rail and the handle");
    }

    /** The handle brings behaviour, never a look: no colour, no line, no word of a design. */
    @Test
    void theHandlesStylesPaintNothing() {
        for (CssClass<SizingStyles> c : SizingStyles.INSTANCE.cssClasses()) {
            String body = c.body();
            assertFalse(body.contains("#") || body.contains("rgb") || body.contains("color") || body.contains("border") || body.contains("background"),
                    c.getClass().getSimpleName() + " paints: " + body);
            assertTrue(c.wears().isEmpty() && c.reads().isEmpty(), c.getClass().getSimpleName() + " wears or reads a design word");
        }
    }
}
