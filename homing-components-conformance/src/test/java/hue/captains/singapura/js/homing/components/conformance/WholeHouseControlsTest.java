package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.design.styling.InstalledStyling;
import hue.captains.singapura.js.homing.ui.controlpanel.ControlCatalogueModule;
import hue.captains.singapura.js.homing.ui.controlpanel.ControlLeavesModule;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The control catalogue a page has, over the whole house's instance (RFC 0067, D51: what a jar's own instance cannot
 * answer - a component of a jar above it - answered here, where every house jar is): the dialog's type of control.
 */
class WholeHouseControlsTest {

    @BeforeAll
    static void theHousesInstance_installed() { InstalledStyling.install(HouseStyles.INSTANCE.styling()); }

    @Test
    void theDialog_isAModalOverlay() {
        String js = String.join("\n", ControlCatalogueModule.INSTANCE.selfContent(null)) + "\n" + String.join("\n", ControlLeavesModule.INSTANCE.selfContent(null));
        assertTrue(java.util.regex.Pattern.compile("\\[\"modal-overlay\", \\[[^\\]]*\"dialog\"").matcher(js).find(), js);
    }
}
