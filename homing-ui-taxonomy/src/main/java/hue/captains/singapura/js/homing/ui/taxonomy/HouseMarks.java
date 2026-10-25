package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Leaf;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.WithRoles;
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



    /** A line between the rows of a list or a menu: one group ruled off from the next. */
    public record Separator() implements Leaf<Mark> {
        public static final Separator INSTANCE = new Separator();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /**
     * The line where two regions of a layout meet: the layout's own, drawn as its look says - as thick
     * as that, or not drawn at all. Never by itself: a boundary is between two regions. Its role says
     * whether the hand may move it; upright, it reads {@code aria-orientation}.
     */
    public record BoundaryLine() implements Leaf<Mark>, WithRoles<HouseRoleSets.Changing> {
        public static final BoundaryLine INSTANCE = new BoundaryLine();
        @Override public Mark parent() { return Mark.INSTANCE; }
        @Override public HouseRoleSets.Changing roleSet() { return HouseRoleSets.Changing.INSTANCE; }
        /** Upright or lying; the pointer over it; the focus on it; in the hand. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Orientation.INSTANCE, HouseStates.Hovered.INSTANCE,
                HouseStates.Focused.INSTANCE, HouseStates.Dragging.INSTANCE); }
    }

    /** A mark between the steps of a run, on one line with them: the sign between a trail's crumbs. */
    public record Delimiter() implements Leaf<Mark> {
        public static final Delimiter INSTANCE = new Delimiter();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** A light that says how something is: lit, dormant, broken. */
    public record Lamp() implements Leaf<Mark> {
        public static final Lamp INSTANCE = new Lamp();
        @Override public Mark parent() { return Mark.INSTANCE; }
        /** Lit: what it watches is where the user is; broken: what it watches is refused. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Current.INSTANCE, HouseStates.Invalid.INSTANCE); }
    }

    /** The brand's mark: a square of its colour, until a site gives a logo. A part of the brand: it is never alone. */
    public record BrandMark() implements Leaf<Mark>, WithRoles<HouseRoleSets.Naming> {
        public static final BrandMark INSTANCE = new BrandMark();
        @Override public Mark parent() { return Mark.INSTANCE; }
        @Override public HouseRoleSets.Naming roleSet() { return HouseRoleSets.Naming.INSTANCE; }
    }

    /** A small sign of a state: the current tab, the holder of the keys. */
    public record Indicator() implements Leaf<Mark> {
        public static final Indicator INSTANCE = new Indicator();
        @Override public Mark parent() { return Mark.INSTANCE; }
    }

    /** Where a dragged thing would land. */
    public record DropMark() implements Leaf<Mark>, WithRoles<HouseRoleSets.Signalling> {
        public static final DropMark INSTANCE = new DropMark();
        @Override public Mark parent() { return Mark.INSTANCE; }
        @Override public HouseRoleSets.Signalling roleSet() { return HouseRoleSets.Signalling.INSTANCE; }
    }

    // ── tracks ───────────────────────────────────────────────────────────



    /** The toggle's groove: the pill a switch's knob slides in, in the accent when the switch is on. Never alone: a switch's part. */
    public record ToggleGroove() implements Leaf<Track>, WithRoles<HouseRoleSets.Scaling> {
        public static final ToggleGroove INSTANCE = new ToggleGroove();
        @Override public Track parent() { return Track.INSTANCE; }
        @Override public HouseRoleSets.Scaling roleSet() { return HouseRoleSets.Scaling.INSTANCE; }
        @Override public List<StateType<?>> states() {
            return List.of(HouseStates.Checked.INSTANCE);
        }
    }

}
