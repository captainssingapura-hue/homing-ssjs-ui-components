package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import hue.captains.singapura.js.homing.ui.elements.UiElementsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.icons.UiIconsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.jarinstance.JarInstance;
import hue.captains.singapura.js.homing.ui.taxonomy.UiElementsComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiIconsComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiSplitGridComponentModule;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

/**
 * The jar's JS tests that serve what is made from an instance's taxonomy - the castings, the leaves, the states: over the
 * jar's own instance (RFC 0067, D51) - ui-splitgrid's components and those of the jars it is built on, ui-elements and ui-icons, each with its look in Base - installed before they run, as a site
 * installs its own at bootstrap.
 */
public abstract class UiSplitGridJsTest extends JsModuleTestBase {

    @BeforeAll
    static void theJarsInstance_installed() {
        JarInstance.INSTANCE.install(List.of(UiElementsComponentModule.INSTANCE, UiIconsComponentModule.INSTANCE, UiSplitGridComponentModule.INSTANCE),
                List.of(UiElementsStyleDeclaration.INSTANCE, UiIconsStyleDeclaration.INSTANCE, UiSplitGridStyleDeclaration.INSTANCE));
    }
}
