package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.ui.panes.HousePanes;
import hue.captains.singapura.js.homing.ui.splitgrid.HouseSplitGrid;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;

import java.util.List;

/**
 * The house's dock, as this module adds it to the house's taxonomy: a split grid whose cells
 * are tabbed panes. In the tree if and only if this module is on the classpath.
 */
public record HouseDocking() implements TaxonomyExtension {

    public static final HouseDocking INSTANCE = new HouseDocking();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(DockGrid.INSTANCE);
    }

    /** A split grid whose cells are tabbed panes: the dock. */
    public record DockGrid() implements Component<HouseBranches.Split> {
        public static final DockGrid INSTANCE = new DockGrid();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Split parent() { return HouseBranches.Split.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseSplitGrid.SplitGrid.INSTANCE).as(HouseShaping.Layout.INSTANCE).one(),
                           DSL.part(HousePanes.MultiTabPane.INSTANCE).as(HouseShaping.Dock.INSTANCE).atLeast(1));
        }
    }
}
