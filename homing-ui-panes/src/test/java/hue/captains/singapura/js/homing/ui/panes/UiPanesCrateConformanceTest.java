package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.conformance.rules.CrateDependencyRule;
import hue.captains.singapura.js.homing.conformance.rules.DefaultJsRulePolicy;
import hue.captains.singapura.js.homing.conformance.rules.OrphanCheck;
import hue.captains.singapura.js.homing.conformance.rules.ServedModule;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.core.util.ResourceReader;
import hue.captains.singapura.js.homing.core.CssClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The crate holds every served module and imports only what it requires; no class holds a value the substrate should bind. */
class UiPanesCrateConformanceTest {

    @Test
    void everyServedModuleIsCrated() {
        assertEquals(List.of(), OrphanCheck.check(UiPanesCrate.INSTANCE));
    }

    @Test
    void importsRespectCrateBoundaries() {
        assertEquals(List.of(), CrateDependencyRule.check(UiPanesCrate.INSTANCE));
    }

    @Test
    void thePanePaintsNothingOfItsOwn() {
        for (CssClass<PaneStyles> c : PaneStyles.INSTANCE.cssClasses()) {
            String body = c.body();
            assertFalse(body.contains("#") || body.contains("rgb") || body.contains("px solid") || body.contains("var(--color"),
                    c.getClass().getSimpleName() + " holds a value the substrate should bind: " + body);
        }
    }

    /** Each served JS module under the lane its crate entry declares: the primitives' DOM-owner discipline, the events' no-DOM one, the line limit for all. */
    @Test
    void everyServedModuleKeepsItsLanesDiscipline() {
        int checked = 0;
        for (var entry : UiPanesCrate.INSTANCE.entries()) {
            if (!(entry.declaredType() instanceof StandardJsModuleType type) || type == StandardJsModuleType.GENERATED_CSS) continue;
            String m = entry.moduleClass();
            String src = String.join("\n", ResourceReader.INSTANCE.getStringsFromResource("homing/js/" + m.replace('.', '/') + ".js"));
            var findings = DefaultJsRulePolicy.INSTANCE.rulesFor(type).checkAll(ServedModule.of(m, type, src));
            assertEquals(List.of(), findings, () -> m + ": "
                    + findings.stream().map(f -> f.rule().value() + "@" + f.line() + ": " + f.message()).toList());
            checked++;
        }
        assertEquals(25, checked, "the pane, the single-tab pane, its tabs' parts, the strip and what it does, the floating pane and what it does, its head and what it does, the tab and what it does, the tab-pane, the register, the hand, the window, the opener and the picker, the events, the drag, the tear, the fit, the keys, the menus, the merge and the source");
    }

    @Test
    void thePaneAndTheStripImportEveryClassDeclared() {
        var imported = java.util.stream.Stream.of(MultiTabPaneModule.INSTANCE.imports(), PaneTabsModule.INSTANCE.imports(), TabStripModule.INSTANCE.imports(),
                                                  FloatingPaneModule.INSTANCE.imports(), TitleBarModule.INSTANCE.imports(),
                                                  TabOpenerModule.INSTANCE.imports(), TabPickerModule.INSTANCE.imports(),
                                                  TabModule.INSTANCE.imports(), TabPaneModule.INSTANCE.imports())
                .flatMap(im -> im.getAllImports().values().stream())
                .flatMap(mi -> mi.allImports().stream()).map(e -> e.getClass().getSimpleName()).toList();
        // the bar ported a step at a time (22 Oct): what its old count, close and landing wore waits for their port - and the
        // icon a chip held, with its own step
        var waiting = java.util.Set.of("mtp_rail", "mtp_strip_tail", "mtp_bar_close", "mtp_pill", "mtp_drop_mark",
                "mtp_chip_icon", "mtp_chip_icon_on",
                "fp_icon", "fp_icon_on", "fp_grip");   // the floating pane's icon and its grip, each with its own step (23 Oct)
        var unimported = new java.util.TreeSet<String>();
        for (CssClass<PaneStyles> c : PaneStyles.INSTANCE.cssClasses())
            if (!imported.contains(c.getClass().getSimpleName())) unimported.add(c.getClass().getSimpleName());
        assertEquals(new java.util.TreeSet<>(waiting), unimported, "declared, and imported by none but those waiting for the bar's port");
    }
}
