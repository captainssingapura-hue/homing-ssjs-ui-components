package hue.captains.singapura.js.homing.ui.dialog;

import hue.captains.singapura.js.homing.conformance.rules.CrateDependencyRule;
import hue.captains.singapura.js.homing.conformance.rules.OrphanCheck;
import hue.captains.singapura.js.homing.core.CssClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The crate holds every served module and imports only what it requires; no class holds a value the substrate should bind. */
class UiDialogCrateConformanceTest {

    @Test
    void everyServedModuleIsCrated() {
        assertEquals(List.of(), OrphanCheck.check(UiDialogCrate.INSTANCE));
    }

    @Test
    void importsRespectCrateBoundaries() {
        assertEquals(List.of(), CrateDependencyRule.check(UiDialogCrate.INSTANCE));
    }

    @Test
    void theModalWindowAndTheFooterPaintNothingOfTheirOwn() {   // each group by its purpose (23 Oct): placement only, their looks their castings'
        var bodies = new java.util.ArrayList<CssClass<?>>();
        bodies.addAll(hue.captains.singapura.js.homing.ui.dialog.modal.ModalStyles.INSTANCE.cssClasses());
        bodies.addAll(hue.captains.singapura.js.homing.ui.dialog.footer.FooterStyles.INSTANCE.cssClasses());
        for (CssClass<?> c : bodies) {
            String body = c.body();
            assertFalse(body.contains("#") || body.contains("rgb") || body.contains("px solid"),
                    c.getClass().getSimpleName() + " holds a value the substrate should bind: " + body);
        }
    }
}
