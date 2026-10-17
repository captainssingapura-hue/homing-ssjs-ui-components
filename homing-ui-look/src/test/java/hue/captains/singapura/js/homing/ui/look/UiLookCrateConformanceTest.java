package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.conformance.rules.CrateDependencyRule;
import hue.captains.singapura.js.homing.conformance.rules.OrphanCheck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The crate holds every served module and imports only what it requires. */
class UiLookCrateConformanceTest {

    @Test
    void everyServedModuleIsCrated() {
        assertEquals(List.of(), OrphanCheck.check(UiLookCrate.INSTANCE));
    }

    @Test
    void importsRespectCrateBoundaries() {
        assertEquals(List.of(), CrateDependencyRule.check(UiLookCrate.INSTANCE));
    }
}
