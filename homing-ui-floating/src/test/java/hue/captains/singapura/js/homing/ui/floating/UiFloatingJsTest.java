package hue.captains.singapura.js.homing.ui.floating;

import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import hue.captains.singapura.js.homing.ui.elements.UiElementsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.icons.UiIconsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.jarinstance.JarInstance;
import hue.captains.singapura.js.homing.ui.taxonomy.UiElementsComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiFloatingComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiIconsComponentModule;

import java.util.List;

/**
 * The jar's JS tests that serve what is made from an instance's taxonomy - the castings, the leaves, the states: over the
 * jar's own instance (RFC 0067, D51) - ui-floating's components and those of the jars it is built on, ui-elements and ui-icons, each with its look in Base, handed to what they serve, as a site hands over its own.
 */
public abstract class UiFloatingJsTest extends JsModuleTestBase {

    /** The jar's own instance, handed to what a test serves from it. */
    protected static final Styling STYLING = JarInstance.INSTANCE.styling(List.of(UiElementsComponentModule.INSTANCE, UiIconsComponentModule.INSTANCE, hue.captains.singapura.js.homing.ui.taxonomy.UiPanesComponentModule.INSTANCE,
                    UiFloatingComponentModule.INSTANCE),
            List.of(UiElementsStyleDeclaration.INSTANCE, UiIconsStyleDeclaration.INSTANCE, hue.captains.singapura.js.homing.ui.panes.UiPanesStyleDeclaration.INSTANCE,
                    UiFloatingStyleDeclaration.INSTANCE));   // the layer's floats, the floating pane, the panes' (23 Oct)
}
