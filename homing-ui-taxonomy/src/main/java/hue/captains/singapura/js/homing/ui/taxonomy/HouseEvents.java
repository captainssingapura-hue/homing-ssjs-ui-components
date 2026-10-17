package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.EventRoot;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.L1_EventBranch;

import java.util.List;

/**
 * The house's tree of events: what happens to its components that is not a change of one of their
 * states, filed by what brings it about. Whole, as the role catalogue is: it covers the common cases,
 * so a component, the house's or a downstream's, offers these, and adds a type of its own only where
 * none here says what it means. A state's change is the state's own event, and no node here; the
 * keys and the focus are the keyboard's steward's, and no component's events. What each means is in
 * {@code meanings/.../HouseEvents.md}.
 */
public final class HouseEvents {

    private HouseEvents() {}

    // ── branches ─────────────────────────────────────────────────────────

    /** What the pointer does to it. */
    public record Pointing() implements L1_EventBranch<EventRoot> {
        public static final Pointing INSTANCE = new Pointing();
        @Override public EventRoot parent() { return EventRoot.INSTANCE; }
    }

    /** What happens as it is taken and moved. */
    public record Dragging() implements L1_EventBranch<EventRoot> {
        public static final Dragging INSTANCE = new Dragging();
        @Override public EventRoot parent() { return EventRoot.INSTANCE; }
    }

    /** What happens to its value as the user gives it. */
    public record Editing() implements L1_EventBranch<EventRoot> {
        public static final Editing INSTANCE = new Editing();
        @Override public EventRoot parent() { return EventRoot.INSTANCE; }
    }

    /** What the user asks of it, beyond its value. */
    public record Asking() implements L1_EventBranch<EventRoot> {
        public static final Asking INSTANCE = new Asking();
        @Override public EventRoot parent() { return EventRoot.INSTANCE; }
    }

    /** What happens as what it shows arrives. */
    public record Loading() implements L1_EventBranch<EventRoot> {
        public static final Loading INSTANCE = new Loading();
        @Override public EventRoot parent() { return EventRoot.INSTANCE; }
    }

    /** What happens as time passes. */
    public record Timing() implements L1_EventBranch<EventRoot> {
        public static final Timing INSTANCE = new Timing();
        @Override public EventRoot parent() { return EventRoot.INSTANCE; }
    }

    /** What happens as what it holds is scrolled. */
    public record Scrolling() implements L1_EventBranch<EventRoot> {
        public static final Scrolling INSTANCE = new Scrolling();
        @Override public EventRoot parent() { return EventRoot.INSTANCE; }
    }

    // ── pointing ─────────────────────────────────────────────────────────

    /** Pressed and let go: the element was clicked. */
    public record Click() implements EventType<Pointing> {
        public static final Click INSTANCE = new Click();
        @Override public Pointing parent() { return Pointing.INSTANCE; }
    }

    /** The hand came down on the element: pressed, not yet let go. */
    public record Press() implements EventType<Pointing> {
        public static final Press INSTANCE = new Press();
        @Override public Pointing parent() { return Pointing.INSTANCE; }
    }

    // ── dragging ─────────────────────────────────────────────────────────

    /** Taken hold of, to be moved. */
    public record Grab() implements EventType<Dragging> {
        public static final Grab INSTANCE = new Grab();
        @Override public Dragging parent() { return Dragging.INSTANCE; }
    }

    /** Moved while held. */
    public record Drag() implements EventType<Dragging> {
        public static final Drag INSTANCE = new Drag();
        @Override public Dragging parent() { return Dragging.INSTANCE; }
    }

    /** Let go where it is, after being moved. */
    public record Drop() implements EventType<Dragging> {
        public static final Drop INSTANCE = new Drop();
        @Override public Dragging parent() { return Dragging.INSTANCE; }
    }

    // ── editing ──────────────────────────────────────────────────────────

    /** Its value changed, the user still at it. */
    public record Input() implements EventType<Editing> {
        public static final Input INSTANCE = new Input();
        @Override public Editing parent() { return Editing.INSTANCE; }
    }

    /** The user is done with its value. */
    public record Commit() implements EventType<Editing> {
        public static final Commit INSTANCE = new Commit();
        @Override public Editing parent() { return Editing.INSTANCE; }
    }

    // ── asking ───────────────────────────────────────────────────────────

    /** The user asks for what was filled in to be sent. */
    public record Submit() implements EventType<Asking> {
        public static final Submit INSTANCE = new Submit();
        @Override public Asking parent() { return Asking.INSTANCE; }
    }

    /** The user asks for it to go away. */
    public record Dismiss() implements EventType<Asking> {
        public static final Dismiss INSTANCE = new Dismiss();
        @Override public Asking parent() { return Asking.INSTANCE; }
    }

    /** The user asks for the thing itself. */
    public record Activate() implements EventType<Asking> {
        public static final Activate INSTANCE = new Activate();
        @Override public Asking parent() { return Asking.INSTANCE; }
    }

    // ── loading ──────────────────────────────────────────────────────────

    /** What it shows has arrived. */
    public record Load() implements EventType<Loading> {
        public static final Load INSTANCE = new Load();
        @Override public Loading parent() { return Loading.INSTANCE; }
    }

    /** What it shows could not arrive. */
    public record Fail() implements EventType<Loading> {
        public static final Fail INSTANCE = new Fail();
        @Override public Loading parent() { return Loading.INSTANCE; }
    }

    // ── timing ───────────────────────────────────────────────────────────

    /** Its time ran out. */
    public record Expire() implements EventType<Timing> {
        public static final Expire INSTANCE = new Expire();
        @Override public Timing parent() { return Timing.INSTANCE; }
    }

    // ── scrolling ────────────────────────────────────────────────────────

    /** Scrolled to an end of what it holds. */
    public record ReachEnd() implements EventType<Scrolling> {
        public static final ReachEnd INSTANCE = new ReachEnd();
        @Override public Scrolling parent() { return Scrolling.INSTANCE; }
    }

    /** Every type of the tree, branch by branch. */
    public static final List<EventType<?>> ALL = List.of(
            Click.INSTANCE, Press.INSTANCE,
            Grab.INSTANCE, Drag.INSTANCE, Drop.INSTANCE,
            Input.INSTANCE, Commit.INSTANCE,
            Submit.INSTANCE, Dismiss.INSTANCE, Activate.INSTANCE,
            Load.INSTANCE, Fail.INSTANCE,
            Expire.INSTANCE,
            ReachEnd.INSTANCE);
}
