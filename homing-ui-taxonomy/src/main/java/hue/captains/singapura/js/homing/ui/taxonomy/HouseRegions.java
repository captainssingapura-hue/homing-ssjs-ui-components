package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Region;

import java.util.List;

/**
 * The house's regions: stretches of a container's room with no frame of their own - a panel's
 * body is played by a {@link Section}, the preferences' index and detail each by a {@link
 * WidgetSlot}.
 */
public final class HouseRegions {

    private HouseRegions() {}

    /** A stretch of a container's room: a head, a body, a foot. */
    public record Section() implements Component<Region> {
        public static final Section INSTANCE = new Section();
        @Override public Region parent() { return Region.INSTANCE; }
    }

    /** A stretch of a view a widget is held in. */
    public record WidgetSlot() implements Component<Region> {
        public static final WidgetSlot INSTANCE = new WidgetSlot();
        @Override public Region parent() { return Region.INSTANCE; }
    }
    // ── a document's blocks: a quote, a block of code, a table - its cells are containers (HouseContainers) ──

    /** Words quoted from elsewhere, set off from the text round them. */
    public record Quote() implements Component<HouseBranches.Region> {
        public static final Quote INSTANCE = new Quote();
        @Override public HouseBranches.Region parent() { return HouseBranches.Region.INSTANCE; }
    }

    /** Lines of code, or of a log, kept as they are written. */
    public record CodeBlock() implements Component<HouseBranches.Region> {
        public static final CodeBlock INSTANCE = new CodeBlock();
        @Override public HouseBranches.Region parent() { return HouseBranches.Region.INSTANCE; }
    }

    /** Facts set out in rows and columns, each column headed. */
    public record Table() implements Component<HouseBranches.Region> {
        public static final Table INSTANCE = new Table();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Region parent() { return HouseBranches.Region.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseContainers.HeaderCell.INSTANCE).as(HouseShaping.Column.INSTANCE).any(),
                           DSL.part(HouseContainers.DataCell.INSTANCE).as(HouseShaping.Cell.INSTANCE).any());
        }
    }

}
