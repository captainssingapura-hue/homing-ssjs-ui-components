package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * A tab's two parts, for the {@link MultiTabPaneModule.MultiTabPane} that holds
 * it: the chip on the strip and the panel in the content — made from the tab,
 * placed in the pane's order, taken out, and named ({@code retitle},
 * {@code reicon}). Static helpers over the pane, as {@link PaneMenusModule} and
 * {@link PaneKeysModule} are; the pane keeps the order and the state.
 *
 * <p>Split out of the pane when a tab's name and icon joined it, which took the
 * pane over the effective-line ceiling: a tab's parts are one concern, and the
 * pane's order, keys and reports another.</p>
 */
public record PaneTabsModule() implements DomModule<PaneTabsModule> {

    public static final PaneTabsModule INSTANCE = new PaneTabsModule();

    /** The helpers. */
    public record PaneTabs() implements Exportable._Class<PaneTabsModule> {}

    @Override
    public ImportsFor<PaneTabsModule> imports() {
        return ImportsFor.<PaneTabsModule>builder()
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_tab_content(),
                        new PaneStyles.mtp_tab_content_hidden()
                ), PaneStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneEventsModule.PaneEvents()), PaneEventsModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PaneTabsModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new PaneTabs()));
    }
}
