package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.EventRoot;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;

import java.util.List;

/**
 * The house's tree of events: what happens to its components that is not a change of one of
 * their states. Harvested from the components there are, and grown as more come: so far a click,
 * for a button and anything else that can be clicked.
 */
public final class HouseEvents {

    private HouseEvents() {}

    /** Pressed and let go: the element was clicked. */
    public record Click() implements EventType<EventRoot> {
        public static final Click INSTANCE = new Click();
        @Override public EventRoot parent() { return EventRoot.INSTANCE; }
    }

    /** Every event of the tree, in its order. */
    public static final List<EventType<?>> ALL = List.of(Click.INSTANCE);
}
