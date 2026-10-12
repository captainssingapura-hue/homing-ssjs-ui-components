package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.sizing.RailModule;
import hue.captains.singapura.js.homing.sizing.SizingHandleModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;
import java.util.List;

/**
 * {@code SplitGrid}: a tree of rows and columns of cells sharing their space
 * by ratio — the tracks — with a line where two cells meet: the house's
 * boundary line, a splitter the hand moves where the grid is made resizable -
 * sizing applied, a rail to each split - and a seam where it is not. A
 * container in the relation grid's sense: the owner mints what goes in a
 * cell ({@code cell(id)} is the box to fill), the grid arranges the cells,
 * subdivides and removes them on request, and reports every change of
 * arrangement as one {@code SplitGridEvents} object. It never knows what a
 * cell holds. A branch component.
 */
public record SplitGridModule() implements DomModule<SplitGridModule>, ImplFor<HouseSplitGrid.SplitGrid> {

    /** The class: {@code new SplitGrid(branch, {host, layout, resizable?, minCellPx?, onEvent?})}; {@code cell}, {@code cells}, {@code layout}, {@code setRatios}, {@code subdivide}, {@code remove}, {@code dispose}. */
    public record SplitGrid() implements BranchComponent<SplitGridModule> {
        @Override public String summary() { return "A grid of cells split and re-split, the arrangement a tree; resizable, its lines moved by hand."; }
    }

    public static final SplitGridModule INSTANCE = new SplitGridModule();

    @Override
    public ImportsFor<SplitGridModule> imports() {
        return ImportsFor.<SplitGridModule>builder()
                .add(new ModuleImports<>(List.of(new SplitGridEventsModule.SplitGridEvents()), SplitGridEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SplitGridTreeModule.SplitGridTree()), SplitGridTreeModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SplitGridGeometryModule.SplitGridGeometry()), SplitGridGeometryModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new RailModule.Rail()), RailModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SizingHandleModule.SizingHandle()), SizingHandleModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new SplitGridStyles.sg_root(),
                        new SplitGridStyles.sg_split(),
                        new SplitGridStyles.sg_split_h(),
                        new SplitGridStyles.sg_split_v(),
                        new SplitGridStyles.sg_child(),
                        new SplitGridStyles.sg_child_h(),
                        new SplitGridStyles.sg_child_v(),
                        new SplitGridStyles.sg_cell(),
                        new SplitGridStyles.sg_line(),
                        new SplitGridStyles.sg_line_fixed()
                ), SplitGridStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SplitGridModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SplitGrid()));
    }
}
