package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Mark;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Track;

import java.util.List;

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
        /** Its colour, moved by its degree: the keys mark carries the accent part of the way into the ink it stands on. */
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** A notch a value may snap to. */
    public record Detent() implements Component<Mark> {
        public static final Detent INSTANCE = new Detent();
        @Override public Mark parent() { return Mark.INSTANCE; }
        /** Shown while what rests on it is in the hand. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Dragging.INSTANCE); }
    }

    /** A scale's tick. */
    public record Tick() implements Component<Mark> {
        public static final Tick INSTANCE = new Tick();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** A line between the rows of a list or a menu: one group ruled off from the next. */
    public record Separator() implements Component<Mark> {
        public static final Separator INSTANCE = new Separator();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /**
     * The line where two regions of a layout meet: the layout's own, drawn as its look says - as thick
     * as that, or not drawn at all. Never by itself: a boundary is between two regions. Its role says
     * whether the hand may move it; upright, it reads {@code aria-orientation}.
     */
    public record BoundaryLine() implements Component<Mark> {
        public static final BoundaryLine INSTANCE = new BoundaryLine();
        @Override public Mark parent() { return Mark.INSTANCE; }
        /** Upright or lying; the pointer over it; the focus on it; in the hand. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Orientation.INSTANCE, HouseStates.Hovered.INSTANCE,
                HouseStates.Focused.INSTANCE, HouseStates.Dragging.INSTANCE); }
    }

    /** A mark between the steps of a run, on one line with them: the sign between a trail's crumbs. */
    public record Delimiter() implements Component<Mark> {
        public static final Delimiter INSTANCE = new Delimiter();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** A light that says how something is: lit, dormant, broken. */
    public record Lamp() implements Component<Mark> {
        public static final Lamp INSTANCE = new Lamp();
        @Override public Mark parent() { return Mark.INSTANCE; }
        /** Lit: what it watches is where the user is; broken: what it watches is refused. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Current.INSTANCE, HouseStates.Invalid.INSTANCE); }
    }

    /** The brand's mark: a square of its colour, until a site gives a logo. A part of the brand: it is never alone. */
    public record BrandMark() implements Component<Mark> {
        public static final BrandMark INSTANCE = new BrandMark();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** A small sign of a state: the current tab, the holder of the keys. */
    public record Indicator() implements Component<Mark> {
        public static final Indicator INSTANCE = new Indicator();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** Where a dragged thing would land. */
    public record DropMark() implements Component<Mark> {
        public static final DropMark INSTANCE = new DropMark();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    // ── tracks ───────────────────────────────────────────────────────────

    /** The slider's rail: the stretch its value is set along, the hand taken anywhere on it - its groove, fill and knob placed on it. Never alone: a slider's part. */
    public record SliderRail() implements Component<Track> {
        public static final SliderRail INSTANCE = new SliderRail();
        @Override public Track parent() { return Track.INSTANCE; }
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Enabled.INSTANCE); }
    }

    /** The slider's groove: the line its knob runs along. Never alone: a slider's part. */
    public record SliderGroove() implements Component<Track> {
        public static final SliderGroove INSTANCE = new SliderGroove();
        @Override public Track parent() { return Track.INSTANCE; }
    }

    /** The toggle's groove: the pill a switch's knob slides in, in the accent when the switch is on. Never alone: a switch's part. */
    public record ToggleGroove() implements Component<Track> {
        public static final ToggleGroove INSTANCE = new ToggleGroove();
        @Override public Track parent() { return Track.INSTANCE; }
        @Override public List<StateType<?>> states() {
            return List.of(HouseStates.Checked.INSTANCE);
        }
    }

    /** The part of a groove a value fills. */
    public record Fill() implements Component<Track> {
        public static final Fill INSTANCE = new Fill();
        @Override public Track parent() { return Track.INSTANCE; }
    }
}
