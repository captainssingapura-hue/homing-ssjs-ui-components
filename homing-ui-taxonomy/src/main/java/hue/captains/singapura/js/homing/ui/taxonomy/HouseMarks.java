package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Mark;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Track;

/**
 * The house's small signs and the lines things move along: independent components, each a role's
 * base wherever a component draws one - a slider's knob runs in a {@link Groove}, a tab shows its
 * state with an {@link Indicator}.
 */
public final class HouseMarks {

    private HouseMarks() {}

    // ── marks ────────────────────────────────────────────────────────────

    /** A glyph from the house's icon vocabulary. */
    public record Icon() implements Component<Mark> {
        public static final Icon INSTANCE = new Icon();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** A notch a value may snap to. */
    public record Detent() implements Component<Mark> {
        public static final Detent INSTANCE = new Detent();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** A scale's tick. */
    public record Tick() implements Component<Mark> {
        public static final Tick INSTANCE = new Tick();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** A line between things in a row or a list. */
    public record Separator() implements Component<Mark> {
        public static final Separator INSTANCE = new Separator();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** A light that says how something is: lit, dormant, broken. */
    public record Lamp() implements Component<Mark> {
        public static final Lamp INSTANCE = new Lamp();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** Where a dragged thing would land. */
    public record DropMark() implements Component<Mark> {
        public static final DropMark INSTANCE = new DropMark();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** A small sign of a state: the current tab, the holder of the keys. */
    public record Indicator() implements Component<Mark> {
        public static final Indicator INSTANCE = new Indicator();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    // ── tracks ───────────────────────────────────────────────────────────

    /** The line a knob runs along. */
    public record Groove() implements Component<Track> {
        public static final Groove INSTANCE = new Groove();
        @Override public Track parent() { return Track.INSTANCE; }
    }

    /** The part of a groove a value fills. */
    public record Fill() implements Component<Track> {
        public static final Fill INSTANCE = new Fill();
        @Override public Track parent() { return Track.INSTANCE; }
    }
}
