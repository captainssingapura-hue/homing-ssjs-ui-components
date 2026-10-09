package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.StateRoot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;

import java.util.List;

/**
 * The house's tree of states: what its components may be, cutting across them. Harvested from
 * the components there are, and grown as more come: so far the buttons' - every button may be
 * off, and a toggle may be on - and a crumb's, the step of a trail that is where the page is. Each state's change is an event a component offers by having the
 * state; no tree holds it.
 */
public final class HouseStates {

    private HouseStates() {}

    /** Usable: off, it is inert and says so. */
    public record Enabled() implements StateType<StateRoot> {
        public static final Enabled INSTANCE = new Enabled();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    /** Pressed and staying so until pressed again: a toggle that is on. */
    public record Pressed() implements StateType<StateRoot> {
        public static final Pressed INSTANCE = new Pressed();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    /** The one of its kind that is where the reader is: the trail's step that is this page. */
    public record Current() implements StateType<StateRoot> {
        public static final Current INSTANCE = new Current();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    /** Every state of the tree, in its order. */
    public static final List<StateType<?>> ALL = List.of(Enabled.INSTANCE, Pressed.INSTANCE, Current.INSTANCE);
}
