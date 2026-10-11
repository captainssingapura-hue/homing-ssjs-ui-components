package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;

import java.util.List;

/**
 * The house's split grid, as this module adds it to the house's taxonomy: the grid, its shape
 * drawn small, and a cell of it. In the tree if and only if this module is on the classpath.
 */
public record HouseSplitGrid() implements TaxonomyExtension {

    public static final HouseSplitGrid INSTANCE = new HouseSplitGrid();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(SplitGrid.INSTANCE, SplitGridMirror.INSTANCE, GridCell.INSTANCE);
    }

    /**
     * A grid of cells split in both directions, a boundary line where two meet - the grid's part, in the
     * house's look: a splitter where the grid is resizable, the hand moving it; a seam where it is not.
     * Its cells are its own.
     */
    public record SplitGrid() implements Component<HouseBranches.Split> {
        public static final SplitGrid INSTANCE = new SplitGrid();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Split parent() { return HouseBranches.Split.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.BoundaryLine.INSTANCE).as(HouseDoing.Splitter.INSTANCE).any(),
                           DSL.part(HouseMarks.BoundaryLine.INSTANCE).as(HouseShaping.Seam.INSTANCE).any());
        }
    }

    /**
     * A split grid's shape, drawn small: a control - it takes the keys, and its cursor picks a pane -
     * whose cells are pictures of the panes, each with its name, as a pane's thumbnail is.
     */
    public record SplitGridMirror() implements Component<HouseBranches.Control> {
        public static final SplitGridMirror INSTANCE = new SplitGridMirror();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Control parent() { return HouseBranches.Control.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PaneThumb.INSTANCE).as(HouseDoing.Choice.INSTANCE).any());
        }
    }

    /** One cell of a grid. */
    public record GridCell() implements Component<HouseBranches.Region> {
        public static final GridCell INSTANCE = new GridCell();
        @Override public HouseBranches.Region parent() { return HouseBranches.Region.INSTANCE; }
    }
}
