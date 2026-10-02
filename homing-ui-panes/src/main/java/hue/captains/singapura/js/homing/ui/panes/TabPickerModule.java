package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * {@code TabPicker}, a transient pane its host owns: the kinds that can be
 * opened, a button each, and Cancel. Picked, it ASKS and opens nothing itself -
 * whoever asked the host for it turns the pick into a request, and the host
 * lets it go. Nothing of a register's: no tab, no widget. It is what a
 * {@link TabOpenerModule.TabOpener} was, without becoming what it opens - a
 * widget is locked to its pane, so no pane turns into another.
 *
 * <p>Its choices are native buttons: the browser's own keys walk and press
 * them, and the first takes the focus when the picker is shown.</p>
 */
public record TabPickerModule() implements DomModule<TabPickerModule> {

    /** The class. */
    public record TabPicker() implements BranchComponent<TabPickerModule> {
        @Override public String summary() { return "A transient pane its host owns: the kinds that can be opened and Cancel - picked, it asks, and opens nothing itself."; }
    }

    public static final TabPickerModule INSTANCE = new TabPickerModule();

    @Override
    public ImportsFor<TabPickerModule> imports() {
        return ImportsFor.<TabPickerModule>builder()
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_opener(),
                        new PaneStyles.mtp_opener_note(),
                        new PaneStyles.mtp_opener_grid(),
                        new PaneStyles.mtp_opener_pick()
                ), PaneStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabPickerModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TabPicker())); }
}
