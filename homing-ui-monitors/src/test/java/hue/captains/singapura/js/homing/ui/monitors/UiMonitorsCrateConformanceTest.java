package hue.captains.singapura.js.homing.ui.monitors;

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

/** The crate holds every served module, imports only what it requires, keeps its lanes, and declares no class it does not import. */
class UiMonitorsCrateConformanceTest {

    @Test
    void everyServedModuleIsCrated() {
        assertEquals(List.of(), OrphanCheck.check(UiMonitorsCrate.INSTANCE));
    }

    @Test
    void importsRespectCrateBoundaries() {
        assertEquals(List.of(), CrateDependencyRule.check(UiMonitorsCrate.INSTANCE));
    }

    @Test
    void everyServedModuleKeepsItsLanesDiscipline_butTheDebtsItsWidgetsDeclare() {
        int checked = 0;
        var raw = new ArrayList<Finding>();
        for (var entry : UiMonitorsCrate.INSTANCE.entries()) {
            var declared = entry.module() instanceof Widget<?, ?> ? StandardJsModuleType.WIDGET : entry.declaredType();
            if (!(declared instanceof StandardJsModuleType type) || type == StandardJsModuleType.GENERATED_CSS) continue;
            String m = entry.moduleClass();
            String src = String.join("\n", ResourceReader.INSTANCE.getStringsFromResource("homing/js/" + m.replace('.', '/') + ".js"));
            raw.addAll(DefaultJsRulePolicy.INSTANCE.rulesFor(type).checkAll(ServedModule.of(m, type, src)));
            checked++;
        }
        assertEquals(2, checked, "the two monitors, widgets both; the styles are a sheet");
        assertEquals(List.of(), DeclaredDebts.INSTANCE.unbalanced(List.of(UiMonitorsCrate.INSTANCE), raw), "a debt owed exactly as declared");
        var errors = FindingGrader.STRICT.grade(raw).stream().filter(GradedFinding::isError).map(g -> g.finding().fingerprint()).toList();
        assertEquals(List.of(), errors, "no finding but what a widget owes");
    }

    /** Every class the sheet declares is imported by one of the two monitors — the fm_ ones by the focus monitor, the sm_ ones by the steward monitor. */
    @Test
    void theMonitorsImportEveryClassDeclared() {
        var byFocus = FocusMonitorModule.INSTANCE.imports().getAllImports().values().stream()
                .flatMap(mi -> mi.allImports().stream()).map(e -> e.getClass().getSimpleName()).toList();
        var bySteward = StewardMonitorModule.INSTANCE.imports().getAllImports().values().stream()
                .flatMap(mi -> mi.allImports().stream()).map(e -> e.getClass().getSimpleName()).toList();
        for (CssClass<FocusStyles> c : FocusStyles.INSTANCE.cssClasses()) {
            String n = c.getClass().getSimpleName();
            assertTrue((n.startsWith("fm_") ? byFocus : bySteward).contains(n), n + " is declared but not imported by its monitor");
        }
    }
}
