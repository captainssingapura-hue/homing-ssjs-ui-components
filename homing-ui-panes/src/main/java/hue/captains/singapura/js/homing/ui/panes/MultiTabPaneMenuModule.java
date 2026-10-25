package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/**
 * A multi-tab pane's tab menu (owner, 23 Oct): a right-press on a chip of its bar opens the page's tab menu at the
 * pointer, through the steward handed to the pane; Close asks the tab's close, as its cross does. One handler for the
 * kind, every pane's. Split from the pane, its line limit reached.
 */
public record MultiTabPaneMenuModule() implements DomModule<MultiTabPaneMenuModule> {

    /** {@code new MultiTabPaneMenu(pane, strip, steward)}; {@code ask(target, x, y)}, {@code drop()}. */
    public record MultiTabPaneMenu() implements Exportable._Class<MultiTabPaneMenuModule> {}

    public static final MultiTabPaneMenuModule INSTANCE = new MultiTabPaneMenuModule();

    @Override public ImportsFor<MultiTabPaneMenuModule> imports() { return ImportsFor.<MultiTabPaneMenuModule>builder().build(); }

    @Override public ExportsOf<MultiTabPaneMenuModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new MultiTabPaneMenu())); }
}
