package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.Layout;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The house's layouts (owner, 21 Oct): a flat list of their own, apart from the component taxonomy. A layout is placed in
 * an umbrella container and arranges its positions, each a sub-container of one kind holding a component or a widget;
 * it is strictly style-agnostic, its splitters designed components it uses as it will, and what it places designed
 * through the layouts' styling ({@link HouseContainers.LayoutStyling}). The split grid, dynamic, is the one so far:
 * its user splits, merges and moves its positions, each a grid cell. Static layouts join as they are first needed.
 */
public record HouseLayouts() implements StatelessFunctionalObject {

    public static final HouseLayouts INSTANCE = new HouseLayouts();

    /** The split grid: dynamic, a grid cell at each of its positions. */
    public static final Layout SPLIT_GRID = new Layout("split-grid", Cardinality.DYNAMIC, HouseContainers.GridCell.INSTANCE);

    /** Every layout the house has, in no order but their names'. */
    public List<Layout> layouts() { return List.of(SPLIT_GRID); }
}
