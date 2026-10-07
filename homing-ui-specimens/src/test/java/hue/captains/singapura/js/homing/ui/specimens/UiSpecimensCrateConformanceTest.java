package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.conformance.rules.CrateDependencyRule;
import hue.captains.singapura.js.homing.conformance.rules.OrphanCheck;
import hue.captains.singapura.js.homing.core.CssClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/** The crate holds every served module and imports only what it requires; its layout paints nothing of its own. */
class UiSpecimensCrateConformanceTest {

    @Test
    void everyServedModuleIsCrated() {
        assertEquals(List.of(), OrphanCheck.check(UiSpecimensCrate.INSTANCE));
    }

    @Test
    void importsRespectCrateBoundaries() {
        assertEquals(List.of(), CrateDependencyRule.check(UiSpecimensCrate.INSTANCE));
    }

    @Test
    void theLayoutPaintsNothingOfItsOwn() {
        for (CssClass<SpecimenStyles> c : SpecimenStyles.INSTANCE.cssClasses()) {
            String body = c.body();
            assertFalse(body.contains("#") || body.contains("rgb") || body.contains("px solid"),
                    c.getClass().getSimpleName() + " holds a value the substrate should bind: " + body);
        }
    }
}
