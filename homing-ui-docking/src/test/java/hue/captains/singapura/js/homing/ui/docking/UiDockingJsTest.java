package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import hue.captains.singapura.js.homing.ui.elements.UiElementsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.floating.UiFloatingStyleDeclaration;
import hue.captains.singapura.js.homing.ui.icons.UiIconsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.jarinstance.JarInstance;
import hue.captains.singapura.js.homing.ui.menu.UiMenuStyleDeclaration;
import hue.captains.singapura.js.homing.ui.panes.UiPanesStyleDeclaration;
import hue.captains.singapura.js.homing.ui.taxonomy.UiElementsComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiFloatingComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiIconsComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiMenuComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPanesComponentModule;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

/**
 * The jar's JS tests that serve what is made from an instance's taxonomy - the castings, the leaves, the states: over the
 * jar's own instance (RFC 0067, D51) - the components of the jars the desk is built on, ui-elements, ui-icons,
 * ui-floating, ui-menu and ui-panes, each with its look in Base - installed before they run, as a site installs its own
 * at bootstrap. The desk has no components of its own.
 */
public abstract class UiDockingJsTest extends JsModuleTestBase {

    @BeforeAll
    static void theJarsInstance_installed() {
        JarInstance.INSTANCE.install(List.of(UiElementsComponentModule.INSTANCE, UiIconsComponentModule.INSTANCE, UiFloatingComponentModule.INSTANCE,
                        UiMenuComponentModule.INSTANCE, UiPanesComponentModule.INSTANCE),
                List.of(UiElementsStyleDeclaration.INSTANCE, UiIconsStyleDeclaration.INSTANCE, UiFloatingStyleDeclaration.INSTANCE,
                        UiMenuStyleDeclaration.INSTANCE, UiPanesStyleDeclaration.INSTANCE));
    }
}
