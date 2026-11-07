package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.conformance.rules.CrateDependencyRule;
import hue.captains.singapura.js.homing.conformance.rules.OrphanCheck;
import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The crate holds every served module and imports only what it requires; every class wears something. */
class UiElementsCrateConformanceTest {

    @Test
    void everyServedModuleIsCrated() {
        assertEquals(List.of(), OrphanCheck.INSTANCE.check(UiElementsCrate.INSTANCE));
    }

    @Test
    void importsRespectCrateBoundaries() {
        assertEquals(List.of(), CrateDependencyRule.INSTANCE.check(UiElementsCrate.INSTANCE));
    }

    @Test
    void theElementsPaintNothingOfTheirOwn() {
        var groups = List.<CssGroup<?>>of(ElementStyles.INSTANCE, ButtonStyles.INSTANCE, CardStyles.INSTANCE, ItemStyles.INSTANCE,
                SiteBarStyles.INSTANCE, SliderStyles.INSTANCE, StatusFooterStyles.INSTANCE, SwitchStyles.INSTANCE);
        for (CssGroup<?> group : groups) for (CssClass<?> c : group.cssClasses()) {
            String body = c.body();
            assertFalse(body.contains("#") || body.contains("rgb") || body.contains("px solid"),
                    c.getClass().getSimpleName() + " holds a value the substrate should bind: " + body);
        }
    }
}
