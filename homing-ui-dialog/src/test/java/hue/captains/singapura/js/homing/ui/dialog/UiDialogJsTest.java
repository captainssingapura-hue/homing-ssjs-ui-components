package hue.captains.singapura.js.homing.ui.dialog;

import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import hue.captains.singapura.js.homing.ui.elements.UiElementsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.floating.UiFloatingStyleDeclaration;
import hue.captains.singapura.js.homing.ui.icons.UiIconsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.jarinstance.JarInstance;
import hue.captains.singapura.js.homing.ui.taxonomy.UiDialogComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiElementsComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiFloatingComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.UiIconsComponentModule;

import java.util.List;

/**
 * The jar's JS tests that serve what is made from an instance's taxonomy - the castings, the leaves, the states: over the
 * jar's own instance (RFC 0067, D51) - ui-dialog's components and those of the jars it is built on, ui-elements and ui-icons, ui-panes - its window the floating pane - and ui-floating, each with its look in Base, handed to what they serve, as a site hands over its own.
 */
public abstract class UiDialogJsTest extends JsModuleTestBase {

    /** The jar's own instance, handed to what a test serves from it. */
    protected static final Styling STYLING = JarInstance.INSTANCE.styling(List.of(UiElementsComponentModule.INSTANCE, UiIconsComponentModule.INSTANCE, hue.captains.singapura.js.homing.ui.taxonomy.UiPanesComponentModule.INSTANCE,
                    UiFloatingComponentModule.INSTANCE, UiDialogComponentModule.INSTANCE),
            List.of(UiElementsStyleDeclaration.INSTANCE, UiIconsStyleDeclaration.INSTANCE, hue.captains.singapura.js.homing.ui.panes.UiPanesStyleDeclaration.INSTANCE,
                    UiFloatingStyleDeclaration.INSTANCE, UiDialogStyleDeclaration.INSTANCE));   // the dialog's window, the floating pane, the panes' (23 Oct)
}
