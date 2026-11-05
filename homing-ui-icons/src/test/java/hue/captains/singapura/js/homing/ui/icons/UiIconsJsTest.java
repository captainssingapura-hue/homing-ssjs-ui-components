package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import hue.captains.singapura.js.homing.ui.jarinstance.JarInstance;
import hue.captains.singapura.js.homing.ui.taxonomy.UiIconsComponentModule;

import java.util.List;

/**
 * The jar's JS tests that serve what is made from an instance's taxonomy - the castings, the leaves, the states: over the
 * jar's own instance (RFC 0067, D51) - ui-icons's components, each with its look in Base, handed to what they serve, as a site hands over its own.
 */
public abstract class UiIconsJsTest extends JsModuleTestBase {

    /** The jar's own instance, handed to what a test serves from it. */
    protected static final Styling STYLING = JarInstance.INSTANCE.styling(List.of(UiIconsComponentModule.INSTANCE),
            List.of(UiIconsStyleDeclaration.INSTANCE));
}
