package hue.captains.singapura.js.homing.ui.controlpanel;

import hue.captains.singapura.js.homing.conformance.rules.CrateDependencyRule;
import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.conformance.rules.DeclaredDebts;
import hue.captains.singapura.js.homing.conformance.rules.DefaultJsRulePolicy;
import hue.captains.singapura.js.homing.conformance.rules.Finding;
import hue.captains.singapura.js.homing.conformance.rules.FindingGrader;
import hue.captains.singapura.js.homing.conformance.rules.GradedFinding;
import hue.captains.singapura.js.homing.conformance.rules.OrphanCheck;
import hue.captains.singapura.js.homing.conformance.rules.ServedModule;
import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.core.util.ResourceReader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    void everyHandWrittenModuleKeepsItsLanesDiscipline_butTheDebtsItsWidgetDeclares() {
        int checked = 0;
        var raw = new ArrayList<Finding>();
        for (var entry : UiControlPanelCrate.INSTANCE.entries()) {
            var declared = entry.module() instanceof Widget<?, ?> ? StandardJsModuleType.WIDGET : entry.declaredType();
            if (!(declared instanceof StandardJsModuleType type) || type == StandardJsModuleType.GENERATED_CSS) continue;
            String m = entry.moduleClass();
            if (m.equals(ControlCatalogueModule.class.getName())) continue;
            String src = String.join("\n", ResourceReader.INSTANCE.getStringsFromResource("homing/js/" + m.replace('.', '/') + ".js"));
            raw.addAll(DefaultJsRulePolicy.INSTANCE.rulesFor(type).checkAll(ServedModule.of(m, type, src)));
            checked++;
        }
        assertEquals(2, checked, "the helper and the panel, a widget; the catalogue is generated, the styles a sheet");
        assertEquals(List.of(), DeclaredDebts.INSTANCE.unbalanced(List.of(UiControlPanelCrate.INSTANCE), raw), "a debt owed exactly as declared");
        var errors = FindingGrader.STRICT.grade(raw).stream().filter(GradedFinding::isError).map(g -> g.finding().fingerprint()).toList();
        assertEquals(List.of(), errors, "no finding but what the widget owes");
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
        assertTrue(js.contains("\"heading4\": \"plain\""), js);
        assertTrue(js.contains("\"danger-button\": Object.freeze([\"colour\", \"size\", \"enabled\"])"), js);
    }
}
