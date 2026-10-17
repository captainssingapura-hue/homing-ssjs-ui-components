package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Leaf;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;

import java.util.List;

/**
 * The house's split grid: the grid, its shape drawn small, and a cell of it. Defined here, with the house's other
 * components, whether or not its implementation is on the classpath; implemented - and drawn by Base - in {@code
 * homing-ui-splitgrid} (RFC 0067, appendix The Style Jars, D40).
 */
public final class HouseSplitGrid {

    private HouseSplitGrid() {}

    /**
     * A grid of cells split in both directions, a boundary line where two meet - the grid's part, in the
     * house's look: a splitter where the grid is resizable, the hand moving it; a seam where it is not.
     * Its cells are its own.
     */
    public record SplitGrid() implements Composite<HouseBranches.Split> {
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
    public record SplitGridMirror() implements Composite<HouseBranches.Selector> {
        public static final SplitGridMirror INSTANCE = new SplitGridMirror();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Selector parent() { return HouseBranches.Selector.INSTANCE; }
        /** Where the page's keys are, held by the steward on its root (RFC 0067, D12). */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Keyboard.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PaneThumb.INSTANCE).as(HouseShaping.Cell.INSTANCE).any());
        }
    }

    /** One cell of a grid. */
    public record GridCell() implements Leaf<HouseBranches.Region> {
        public static final GridCell INSTANCE = new GridCell();
        @Override public HouseBranches.Region parent() { return HouseBranches.Region.INSTANCE; }
    }
}
