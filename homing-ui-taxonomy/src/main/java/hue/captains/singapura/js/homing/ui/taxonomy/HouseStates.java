package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Classed;
import hue.captains.singapura.js.homing.component.taxonomy.Flag;
import hue.captains.singapura.js.homing.component.taxonomy.Importance;
import hue.captains.singapura.js.homing.component.taxonomy.L1_StateBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Pseudo;
import hue.captains.singapura.js.homing.component.taxonomy.StateRoot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.StateValue;

import java.util.List;
import java.util.Map;

/**
 * The house's tree of states: the respects in which its components may be one way or another,
 * cutting across them, filed by what they are about. Whole, as the role catalogue is: it covers the
 * common cases - the states the components of a common library have, and the ones the page's
 * machinery keeps - so a component, the house's or a downstream's, is built from these, and adds a
 * type of its own only where none here says what it means.
 *
 * <p>The tree files the types, never their values: a component that has a type is in exactly one of
 * its values at a time, and has one value of each type it names. A type that is yes or no is a
 * {@link Flag}, false at rest unless it says otherwise; the rest are named. A design answers each
 * value but the rest by its word: the established design word where there is one - Enabled's false
 * is {@code disabled}, Hovered's true {@code hover}, Down's true {@code active} - and the type's name
 * and the value's otherwise ({@code busy-true}). Each type's change is an
 * event a component offers by having the type; no tree holds it. What each means is in {@code
 * meanings/.../HouseStates.md}.</p>
 */
public final class HouseStates {

    private HouseStates() {}

    // ── branches ─────────────────────────────────────────────────────────

    /** Whether the user can use it, and how far. */
    public record Availability() implements L1_StateBranch<StateRoot> {
        public static final Availability INSTANCE = new Availability();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    /** Whether it is chosen, and how. */
    public record Picking() implements L1_StateBranch<StateRoot> {
        public static final Picking INSTANCE = new Picking();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    /** Whether what it opens is open, or it is itself on view. */
    public record Disclosure() implements L1_StateBranch<StateRoot> {
        public static final Disclosure INSTANCE = new Disclosure();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    /** Whether what it holds will do. */
    public record Validity() implements L1_StateBranch<StateRoot> {
        public static final Validity INSTANCE = new Validity();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    /** Whether it is where the user is, what the user is about to pick, or lit by something else. */
    public record Place() implements L1_StateBranch<StateRoot> {
        public static final Place INSTANCE = new Place();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    /** Whether it holds anything. */
    public record Content() implements L1_StateBranch<StateRoot> {
        public static final Content INSTANCE = new Content();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    /** What the user's hand and keys are doing with it now. */
    public record Interaction() implements L1_StateBranch<StateRoot> {
        public static final Interaction INSTANCE = new Interaction();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    /** Where it sits in the page's depth. */
    public record Physical() implements L1_StateBranch<StateRoot> {
        public static final Physical INSTANCE = new Physical();
        @Override public StateRoot parent() { return StateRoot.INSTANCE; }
    }

    // ── availability ─────────────────────────────────────────────────────

    /** Usable: true at rest; off, it is inert and says so. */
    public record Enabled() implements Flag<Availability>, Importance.Quaternary {
        public static final Enabled INSTANCE = new Enabled();
        @Override public Availability parent() { return Availability.INSTANCE; }
        @Override public boolean rest() { return true; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.FALSE, "disabled"); }
    }

    /** Read, reached and copied, but not changed. */
    public record ReadOnly() implements Flag<Availability>, Importance.Quaternary {
        public static final ReadOnly INSTANCE = new ReadOnly();
        @Override public Availability parent() { return Availability.INSTANCE; }
    }

    /** Working: what it shows, or what it was asked to do, is on the way. */
    public record Busy() implements Flag<Availability>, Importance.Quaternary {
        public static final Busy INSTANCE = new Busy();
        @Override public Availability parent() { return Availability.INSTANCE; }
    }

    // ── picking ──────────────────────────────────────────────────────────

    /** Pressed and staying so until pressed again: a toggle that is on. */
    public record Pressed() implements Flag<Picking>, Importance.Tertiary {
        public static final Pressed INSTANCE = new Pressed();
        @Override public Picking parent() { return Picking.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "pressed"); }
    }

    /** Chosen by a mark of its own: a box ticked, a radio filled, a switch on. */
    public record Checked() implements Flag<Picking>, Importance.Tertiary {
        public static final Checked INSTANCE = new Checked();
        @Override public Picking parent() { return Picking.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "checked"); }
    }

    /** How much of the group it speaks for is chosen: none, some or all. The mixed checkbox's, never a plain one's. */
    public record Coverage() implements Classed<Picking>, Importance.Tertiary {
        public static final Coverage INSTANCE = new Coverage();
        private static final List<StateValue> VALUES = List.of(new StateValue("none"), new StateValue("some"), new StateValue("all"));
        @Override public Picking parent() { return Picking.INSTANCE; }
        @Override public List<StateValue> values() { return VALUES; }
    }

    /** Picked from among its kind: an option, a tab, a day, a row. */
    public record Selected() implements Flag<Picking>, Importance.Tertiary {
        public static final Selected INSTANCE = new Selected();
        @Override public Picking parent() { return Picking.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "selected"); }
    }

    // ── disclosure ───────────────────────────────────────────────────────

    /** What it opens is open: said on what opens it. */
    public record Expanded() implements Flag<Disclosure>, Importance.Tertiary {
        public static final Expanded INSTANCE = new Expanded();
        @Override public Disclosure parent() { return Disclosure.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "expanded"); }
    }

    /** On view over the page for now: said on what is opened. */
    public record Shown() implements Flag<Disclosure>, Importance.Tertiary {
        public static final Shown INSTANCE = new Shown();
        @Override public Disclosure parent() { return Disclosure.INSTANCE; }
    }

    // ── validity ─────────────────────────────────────────────────────────

    /** Must be given before what it belongs to is sent. */
    public record Required() implements Flag<Validity>, Importance.Quaternary {
        public static final Required INSTANCE = new Required();
        @Override public Validity parent() { return Validity.INSTANCE; }
    }

    /** What it holds is refused. */
    public record Invalid() implements Flag<Validity>, Importance.Tertiary {
        public static final Invalid INSTANCE = new Invalid();
        @Override public Validity parent() { return Validity.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "invalid"); }
    }

    // ── place ────────────────────────────────────────────────────────────

    /** The one of its kind that is where the user is: the trail's step that is this page. */
    public record Current() implements Flag<Place>, Importance.Tertiary {
        public static final Current INSTANCE = new Current();
        @Override public Place parent() { return Place.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "current"); }
    }

    /** Lit from elsewhere: a search's hit, the rows a chart points at. */
    public record Highlighted() implements Flag<Place>, Importance.Tertiary {
        public static final Highlighted INSTANCE = new Highlighted();
        @Override public Place parent() { return Place.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "highlighted"); }
    }

    /** The one the user is on among its kind, not yet chosen: the option Enter would pick. */
    public record Pointed() implements Flag<Place>, Importance.Tertiary {
        public static final Pointed INSTANCE = new Pointed();
        @Override public Place parent() { return Place.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "pointed"); }
    }

    // ── content ──────────────────────────────────────────────────────────

    /** Holds nothing yet. */
    public record Empty() implements Flag<Content>, Importance.Quaternary {
        public static final Empty INSTANCE = new Empty();
        @Override public Content parent() { return Content.INSTANCE; }
    }

    // ── interaction ──────────────────────────────────────────────────────

    /** The pointer is over it. */
    public record Hovered() implements Flag<Interaction>, Importance.Quinary, Pseudo {
        @Override public String pseudoClass() { return ":hover"; }
        public static final Hovered INSTANCE = new Hovered();
        @Override public Interaction parent() { return Interaction.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "hover"); }
    }

    /** The browser's focus is on it, and shown. */
    public record Focused() implements Flag<Interaction>, Importance.Quinary, Pseudo {
        @Override public String pseudoClass() { return ":focus-visible"; }
        public static final Focused INSTANCE = new Focused();
        @Override public Interaction parent() { return Interaction.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "focus"); }
    }

    /** Being pressed now: the hand is down on it, not yet let go. */
    public record Down() implements Flag<Interaction>, Importance.Primary, Pseudo {
        @Override public String pseudoClass() { return ":active"; }
        public static final Down INSTANCE = new Down();
        @Override public Interaction parent() { return Interaction.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "active"); }
    }

    /** Where the page's keys are, as the keyboard's steward says: away, candidate, lent or held. */
    public record Keyboard() implements Classed<Interaction>, Importance.Secondary {
        public static final Keyboard INSTANCE = new Keyboard();
        private static final List<StateValue> VALUES = List.of(new StateValue("away"), new StateValue("candidate"),
                new StateValue("lent"), new StateValue("held"));
        @Override public Interaction parent() { return Interaction.INSTANCE; }
        @Override public List<StateValue> values() { return VALUES; }
        @Override public Map<StateValue, String> words() { return Map.of(VALUES.get(1), "candidate", VALUES.get(2), "lent", VALUES.get(3), "held"); }
    }

    /** Held by the hand and moved: from the press that grabs it to the release. */
    public record Dragging() implements Flag<Interaction>, Importance.Secondary {
        public static final Dragging INSTANCE = new Dragging();
        @Override public Interaction parent() { return Interaction.INSTANCE; }
        @Override public Map<StateValue, String> words() { return Map.of(StateValue.TRUE, "dragging"); }
    }

    // ── physical ─────────────────────────────────────────────────────────

    /** How far off its own plane it sits: flat, elevated or sunken. */
    public record Elevation() implements Classed<Physical>, Importance.Senary {
        public static final Elevation INSTANCE = new Elevation();
        private static final List<StateValue> VALUES = List.of(new StateValue("flat"), new StateValue("elevated"), new StateValue("sunken"));
        @Override public Physical parent() { return Physical.INSTANCE; }
        @Override public List<StateValue> values() { return VALUES; }
        @Override public Map<StateValue, String> words() { return Map.of(VALUES.get(1), "elevated", VALUES.get(2), "sunken"); }
    }

    /** Which way a thing laid along a line runs: horizontal, or vertical - stood up, as a fader. */
    public record Orientation() implements Classed<Physical>, Importance.Senary {
        public static final Orientation INSTANCE = new Orientation();
        private static final List<StateValue> VALUES = List.of(new StateValue("horizontal"), new StateValue("vertical"));
        @Override public Physical parent() { return Physical.INSTANCE; }
        @Override public List<StateValue> values() { return VALUES; }
        @Override public Map<StateValue, String> words() { return Map.of(VALUES.get(1), "vertical"); }
    }

    /** Every type of the tree, branch by branch. */
    public static final List<StateType<?>> ALL = List.of(
            Enabled.INSTANCE, ReadOnly.INSTANCE, Busy.INSTANCE,
            Pressed.INSTANCE, Checked.INSTANCE, Coverage.INSTANCE, Selected.INSTANCE,
            Expanded.INSTANCE, Shown.INSTANCE,
            Required.INSTANCE, Invalid.INSTANCE,
            Current.INSTANCE, Highlighted.INSTANCE, Pointed.INSTANCE,
            Empty.INSTANCE,
            Hovered.INSTANCE, Focused.INSTANCE, Down.INSTANCE, Keyboard.INSTANCE, Dragging.INSTANCE,
            Elevation.INSTANCE, Orientation.INSTANCE);
}
