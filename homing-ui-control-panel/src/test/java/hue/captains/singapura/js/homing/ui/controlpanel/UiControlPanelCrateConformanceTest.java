package hue.captains.singapura.js.homing.ui.controlpanel;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The crate holds every served module, imports only what it requires, and keeps its lanes; the
 * panel imports every class the sheet declares; and the catalogue a page has is the declarations',
 * generated - every option with its means, rest and method, every type, every leaf's type.
 */
class UiControlPanelCrateConformanceTest {

    @Test
    void everyServedModuleIsCrated() {
        assertEquals(List.of(), OrphanCheck.check(UiControlPanelCrate.INSTANCE));
    }

    @Test
    void importsRespectCrateBoundaries() {
        assertEquals(List.of(), CrateDependencyRule.check(UiControlPanelCrate.INSTANCE));
    }

    /** The hand-written modules keep their lanes; the catalogue is generated, and has no file to read. */
    @Test
    void everyHandWrittenModuleKeepsItsLanesDiscipline() {
        int checked = 0;
        for (var entry : UiControlPanelCrate.INSTANCE.entries()) {
            if (!(entry.declaredType() instanceof StandardJsModuleType type) || type == StandardJsModuleType.GENERATED_CSS) continue;
            String m = entry.moduleClass();
            if (m.equals(ControlCatalogueModule.class.getName())) continue;
            String src = String.join("\n", ResourceReader.INSTANCE.getStringsFromResource("homing/js/" + m.replace('.', '/') + ".js"));
            var findings = DefaultJsRulePolicy.INSTANCE.rulesFor(type).checkAll(ServedModule.of(m, type, src));
            assertEquals(List.of(), findings, () -> m + ": "
                    + findings.stream().map(f -> f.rule().value() + "@" + f.line() + ": " + f.message()).toList());
            checked++;
        }
        assertEquals(2, checked, "the helper and the panel; the catalogue is generated, the styles a sheet");
    }

    @Test
    void thePanelImportsEveryClassDeclared() {
        var imported = ControlPanelModule.INSTANCE.imports().getAllImports().values().stream()
                .flatMap(mi -> mi.allImports().stream()).map(e -> e.getClass().getSimpleName()).toList();
        for (CssClass<ControlPanelStyles> c : ControlPanelStyles.INSTANCE.cssClasses())
            assertTrue(imported.contains(c.getClass().getSimpleName()), c.getClass().getSimpleName() + " is declared but not imported by the panel");
    }

    @Test
    void theCatalogueAPageHas_isTheDeclarations() {
        String js = String.join("\n", ControlCatalogueModule.INSTANCE.selfContent(null));
        assertTrue(js.contains("\"size\": Object.freeze({ category: \"degrees\", means: \"extent\", rest: 0, method: \"size\", label: \"Size\" })"), js);
        assertTrue(js.contains("\"colour\": Object.freeze({ category: \"degrees\", means: \"extent\", rest: 1, method: \"extent\", label: \"Colour\" })"), js);
        assertTrue(js.contains("\"enabled\": Object.freeze({ category: \"states\", means: \"switch\", rest: true, method: \"setOn\", label: \"Enabled\" })"), js);
        assertTrue(js.contains("\"ask\": Object.freeze({ category: \"asking\", means: \"question\", rest: null, method: \"ask\", label: \"What does it show?\" })"), js);
        assertTrue(js.contains("\"modal-overlay\": Object.freeze([\"open-modal\", \"open\", \"close\"])"), js);
        assertTrue(js.contains("\"plain\": Object.freeze([])"), js);
        assertTrue(js.contains("\"dialog\": \"modal-overlay\""), js);
        assertTrue(js.contains("\"danger-button\": \"switchable\""), js);
        assertTrue(js.contains("\"heading\": \"plain\""), js);
    }
}
