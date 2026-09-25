package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.panes.MultiTabPaneModule;

import java.util.List;

/**
 * {@code Floater}, a float: a frame on the desk around a multi-tab pane of its
 * own, with one bar, the pane's strip, as a browser's window has. The strip's
 * ground moves the frame, the cross at the bar's end closes the float and
 * every tab-pane in it, and an empty float closes by itself. The frame is the
 * desk's — headless, never offered to a dock, not closed by the desk's Escape
 * — so its id is the desk's own. RFC 0066 E3, appendix "tab-panes", §7.
 */
public record FloaterModule() implements DomModule<FloaterModule> {

    public static final FloaterModule INSTANCE = new FloaterModule();

    /** The class. */
    public record Floater() implements Exportable._Class<FloaterModule> {}

    @Override
    public ImportsFor<FloaterModule> imports() {
        return ImportsFor.<FloaterModule>builder()
                .add(new ModuleImports<>(List.of(new MultiTabPaneModule.MultiTabPane()), MultiTabPaneModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<FloaterModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Floater()));
    }
}
