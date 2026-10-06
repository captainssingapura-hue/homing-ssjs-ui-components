package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseKinds.Region;

/**
 * The house's regions: stretches of a container's room with no frame of their own - a card's
 * head, body and foot are each played by a {@link Section}, a split grid's cells by a
 * {@link GridCell}.
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
}
