package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.menu.NeedContextMenu;
import hue.captains.singapura.js.homing.ui.menu.tree.ContextMenuKind;
import hue.captains.singapura.js.homing.ui.panes.MultiTabPaneModule;
import hue.captains.singapura.js.homing.ui.panes.PaneMergeModule;
import hue.captains.singapura.js.homing.ui.splitgrid.SplitGridModule;

import java.util.List;
import java.util.Set;

/**
 * {@code DockGrid}: a desk's docks laid out in a split grid — a region is a
 * cell of the grid and a dock in it, nothing between — with what parting and
 * merging them does, said once for every page that holds a workspace: the
 * gallery's docking page and the workspace are built from it (RFC 0066 E3,
 * the workspace detour). The desk is the holder's; the grid adds its docks
 * to it and takes them off as their regions go.
 */
public record DockGridModule() implements DomModule<DockGridModule> {

    /** The class: {@code new DockGrid(branch, { host, desk, layout?, menus?, dock?, chooseRegion?, onEvent? })}. */
    public record DockGrid() implements BranchComponent<DockGridModule>, NeedContextMenu {
        @Override public String summary() { return "Docks in a split grid over a desk: a region's tab bar parts it beside or below, merges it into another, or closes it."; }
        /** The ground menu of a region's tab bar, answered here: the page hands the grid its steward, and the site serving the grid serves the kind. */
        @Override public Set<ContextMenuKind<?>> required() { return Set.of(SplitMenu.INSTANCE); }
    }

    public static final DockGridModule INSTANCE = new DockGridModule();

    @Override
    public ImportsFor<DockGridModule> imports() {
        return ImportsFor.<DockGridModule>builder()
                .add(new ModuleImports<>(List.of(new SplitGridModule.SplitGrid()), SplitGridModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MultiTabPaneModule.MultiTabPane()), MultiTabPaneModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneMergeModule.PaneMerge()), PaneMergeModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<DockGridModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new DockGrid()));
    }
}
