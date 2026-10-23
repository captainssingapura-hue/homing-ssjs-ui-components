package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/**
 * A multi-tab pane's floaters, the pane's own: torn off its bar and carried, taken back or left afloat, docked again by
 * their heads, and another pane's tab caught on its bar (owner, 23 Oct) - split from the pane, its line limit reached
 * (23 Oct).
 */
public record MultiTabPaneFloatsModule() implements DomModule<MultiTabPaneFloatsModule> {

    /** {@code new MultiTabPaneFloats(pane)}; {@code tear}, {@code handAt}, {@code handUp}, {@code inHand}, {@code forget}, {@code land}, {@code expect}, {@code drop}. */
    public record MultiTabPaneFloats() implements Exportable._Class<MultiTabPaneFloatsModule> {}

    public static final MultiTabPaneFloatsModule INSTANCE = new MultiTabPaneFloatsModule();

    @Override public ImportsFor<MultiTabPaneFloatsModule> imports() { return ImportsFor.<MultiTabPaneFloatsModule>builder().build(); }

    @Override public ExportsOf<MultiTabPaneFloatsModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new MultiTabPaneFloats())); }
}
