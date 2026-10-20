package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Leaf;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;

import java.util.List;

/**
 * The house's regions: a document's blocks, each a look of its own. A stretch of a container's room with no look
 * but its owner's spacing is no region and no part - it is its owner's room (Component.rooms(): a panel's body, a
 * slider group's rack, a strip's tail, a site's page; owner, 20 Oct).
 */
public final class HouseRegions {

    private HouseRegions() {}

    // ── a document's blocks: a quote, a block of code, a table - its cells are containers (HouseContainers) ──

    /** Words quoted from elsewhere, set off from the text round them. */
    public record Quote() implements Leaf<HouseBranches.Region> {
        public static final Quote INSTANCE = new Quote();
        @Override public HouseBranches.Region parent() { return HouseBranches.Region.INSTANCE; }
    }

    /** Lines of code, or of a log, kept as they are written. */
    public record CodeBlock() implements Leaf<HouseBranches.Region> {
        public static final CodeBlock INSTANCE = new CodeBlock();
        @Override public HouseBranches.Region parent() { return HouseBranches.Region.INSTANCE; }
    }

    /** Facts set out in rows and columns, each column headed. */
    public record Table() implements Composite<HouseBranches.Region> {
        public static final Table INSTANCE = new Table();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Region parent() { return HouseBranches.Region.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseContainers.HeaderCell.INSTANCE).as(HouseShaping.Column.INSTANCE).any(),
                           DSL.part(HouseContainers.DataCell.INSTANCE).as(HouseShaping.Cell.INSTANCE).any());
        }
    }

}
