package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.panes.MultiTabPaneModule;
import hue.captains.singapura.js.homing.ui.panes.PaneMergeModule;

import java.util.List;

/**
 * A desk's docks residing in a split grid, one dock to a cell: the desk's docking, in a module of
 * its own that the desk imports - {@code desk.dock(branch, grid, opts)} makes one. The grid is the
 * layout and the holder's; a dock is a cell's resident; parting, merging and closing a region are a
 * layout edit and the desk's moves of the tabs, said once here.
 */
public record GridDockingModule() implements DomModule<GridDockingModule> {

    /** The class: made by the desk, never by anyone else. */
    public record GridDocking() implements Exportable._Class<GridDockingModule> {}

    public static final GridDockingModule INSTANCE = new GridDockingModule();

    @Override
    public ImportsFor<GridDockingModule> imports() {
        return ImportsFor.<GridDockingModule>builder()
                .add(new ModuleImports<>(List.of(new MultiTabPaneModule.MultiTabPane()), MultiTabPaneModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneMergeModule.PaneMerge()), PaneMergeModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<GridDockingModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new GridDocking()));
    }
}
