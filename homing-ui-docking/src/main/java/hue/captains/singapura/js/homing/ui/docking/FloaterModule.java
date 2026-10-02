package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.panes.SingleTabPaneModule;

import java.util.List;

/**
 * {@code Floater}, a float: a frame on the desk around a single-tab pane of
 * its own - one tab in transit, never more. Its one bar is the tab's chip,
 * and the whole bar, the chip with it, moves the frame; the chip's cross
 * closes the tab and the float with it, and an empty float closes by itself.
 * A float is never a drop target. The frame is the desk's - headless, not
 * closed by the desk's Escape - so its id is the desk's own. RFC 0066 E3,
 * appendix "tab-panes", section 7.
 */
public record FloaterModule() implements DomModule<FloaterModule> {

    public static final FloaterModule INSTANCE = new FloaterModule();

    /** The class. */
    public record Floater() implements Exportable._Class<FloaterModule> {}

    @Override
    public ImportsFor<FloaterModule> imports() {
        return ImportsFor.<FloaterModule>builder()
                .add(new ModuleImports<>(List.of(new SingleTabPaneModule.SingleTabPane()), SingleTabPaneModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<FloaterModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Floater()));
    }
}
