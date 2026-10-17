package hue.captains.singapura.js.homing.layout;

import hue.captains.singapura.js.homing.conformance.rules.CrateDependencyRule;
import hue.captains.singapura.js.homing.conformance.rules.DefaultJsRulePolicy;
import hue.captains.singapura.js.homing.conformance.rules.OrphanCheck;
import hue.captains.singapura.js.homing.conformance.rules.ServedModule;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.core.util.ResourceReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The crate serves its one module, imports nothing it does not require, and keeps pure logic's discipline. */
class LayoutCrateConformanceTest {

    @Test
    void everyServedModuleIsCrated() {
        assertEquals(List.of(), OrphanCheck.check(LayoutCrate.INSTANCE));
    }

    @Test
    void importsRespectCrateBoundaries() {
        assertEquals(List.of(), CrateDependencyRule.check(LayoutCrate.INSTANCE));
    }

    @Test
    void theAlgebra_keepsPureLogicsDiscipline() {
        for (var entry : LayoutCrate.INSTANCE.entries()) {
            var type = (StandardJsModuleType) entry.declaredType();
            String m = entry.moduleClass();
            String src = String.join("\n", ResourceReader.INSTANCE.getStringsFromResource("homing/js/" + m.replace('.', '/') + ".js"));
            var findings = DefaultJsRulePolicy.INSTANCE.rulesFor(type).checkAll(ServedModule.of(m, type, src));
            assertEquals(List.of(), findings, () -> m + ": " + findings.stream().map(f -> f.rule().value() + "@" + f.line() + ": " + f.message()).toList());
        }
    }
}
