package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Region;

/**
 * The house's regions: stretches of a container's room with no frame of their own - a panel's
 * head is played by a {@link Section}, a split grid mirror's cells by a {@link GridCell}, the
 * preferences' index and detail each by a {@link WidgetSlot}.
 */
public final class HouseRegions {

    private HouseRegions() {}

    /** A stretch of a container's room: a head, a body, a foot. */
    public record Section() implements Component<Region> {
        public static final Section INSTANCE = new Section();
        @Override public Region parent() { return Region.INSTANCE; }
    }

    /** One cell of a grid. */
    public record GridCell() implements Component<Region> {
        public static final GridCell INSTANCE = new GridCell();
        @Override public Region parent() { return Region.INSTANCE; }
    }

    /** A stretch of a view a widget is held in. */
    public record WidgetSlot() implements Component<Region> {
        public static final WidgetSlot INSTANCE = new WidgetSlot();
        @Override public Region parent() { return Region.INSTANCE; }
    }
}
