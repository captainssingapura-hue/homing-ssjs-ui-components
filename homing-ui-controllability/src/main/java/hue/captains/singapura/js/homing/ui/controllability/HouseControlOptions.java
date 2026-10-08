package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;

/**
 * The house's control options, filed by what each does to a component: set it by degree, change
 * its state, show it, view it, arrange it, place it, change what it shows, ask it. Eight categories
 * and twenty-four options. Each names the method on what is controlled that applies it, the
 * method the house's components already answer by where they have one - a button is switched by
 * {@code setOn}, a panel made current by {@code highlight}, a strip held shown by {@code hold}.
 */
public final class HouseControlOptions {

    private HouseControlOptions() {}

    // ── Degrees — set by a number, −1 to 1: the core's axes ───────────────

    /** Set by degree: the core's axes, each a number from −1 to 1. */
    public record Degrees() implements L1_ControlBranch<ControlRoot> {
        public static final Degrees INSTANCE = new Degrees();
        @Override public ControlRoot parent() { return ControlRoot.INSTANCE; }
    }

    /** How much of its colour's meaning it carries: a button's {@code extent}. */
    public record Colour() implements WithExtent<Degrees> {
        public static final Colour INSTANCE = new Colour();
        @Override public Degrees parent() { return Degrees.INSTANCE; }
        @Override public ExtentAxis axis() { return ExtentAxis.COLOUR; }
        @Override public String method() { return "extent"; }
    }

    /** How big it is. */
    public record Size() implements WithExtent<Degrees> {
        public static final Size INSTANCE = new Size();
        @Override public Degrees parent() { return Degrees.INSTANCE; }
        @Override public ExtentAxis axis() { return ExtentAxis.SIZE; }
    }

    /** How wide, against how tall. */
    public record Aspect() implements WithExtent<Degrees> {
        public static final Aspect INSTANCE = new Aspect();
        @Override public Degrees parent() { return Degrees.INSTANCE; }
        @Override public ExtentAxis axis() { return ExtentAxis.ASPECT; }
    }

    // ── States — switched on or off, and kept ─────────────────────────────

    /** A state it is in or not, switched and kept. */
    public record States() implements L1_ControlBranch<ControlRoot> {
        public static final States INSTANCE = new States();
        @Override public ControlRoot parent() { return ControlRoot.INSTANCE; }
    }

    /** It answers: off, it is inert and says so. On until switched. */
    public record Enabled() implements WithSwitch<States> {
        public static final Enabled INSTANCE = new Enabled();
        @Override public States parent() { return States.INSTANCE; }
        @Override public boolean rest() { return true; }
        @Override public String method() { return "setOn"; }
    }

    /** It is the one in use, among others like it: a panel's {@code highlight}. */
    public record Current() implements WithSwitch<States> {
        public static final Current INSTANCE = new Current();
        @Override public States parent() { return States.INSTANCE; }
        @Override public boolean rest() { return false; }
        @Override public String method() { return "highlight"; }
    }

    /** It sits off its own plane, lifted above what is around it. */
    public record Raised() implements WithSwitch<States> {
        public static final Raised INSTANCE = new Raised();
        @Override public States parent() { return States.INSTANCE; }
        @Override public boolean rest() { return false; }
    }

    /** It stays shown, where it would hide again by itself: a strip's {@code hold}. */
    public record Held() implements WithSwitch<States> {
        public static final Held INSTANCE = new Held();
        @Override public States parent() { return States.INSTANCE; }
        @Override public boolean rest() { return false; }
        @Override public String label() { return "Held shown"; }
        @Override public String method() { return "hold"; }
    }

    // ── Showing — opened over the page, and closed ────────────────────────

    /** Opened over the page, and closed. */
    public record Showing() implements L1_ControlBranch<ControlRoot> {
        public static final Showing INSTANCE = new Showing();
        @Override public ControlRoot parent() { return ControlRoot.INSTANCE; }
    }

    /** Opened, holding the page behind back until it is settled. */
    public record OpenModal() implements WithAction<Showing> {
        public static final OpenModal INSTANCE = new OpenModal();
        @Override public Showing parent() { return Showing.INSTANCE; }
        @Override public String label() { return "Open, modal"; }
    }

    /** Opened, the page behind still answering. */
    public record Open() implements WithAction<Showing> {
        public static final Open INSTANCE = new Open();
        @Override public Showing parent() { return Showing.INSTANCE; }
    }

    /** The one in front closed. */
    public record Close() implements WithAction<Showing> {
        public static final Close INSTANCE = new Close();
        @Override public Showing parent() { return Showing.INSTANCE; }
    }

    // ── Viewing — how much of it is seen ──────────────────────────────────

    /** How much of what it shows is seen, and how near. */
    public record Viewing() implements L1_ControlBranch<ControlRoot> {
        public static final Viewing INSTANCE = new Viewing();
        @Override public ControlRoot parent() { return ControlRoot.INSTANCE; }
    }

    /** Nearer, by a step. */
    public record ZoomIn() implements WithAction<Viewing> {
        public static final ZoomIn INSTANCE = new ZoomIn();
        @Override public Viewing parent() { return Viewing.INSTANCE; }
    }

    /** Further, by a step. */
    public record ZoomOut() implements WithAction<Viewing> {
        public static final ZoomOut INSTANCE = new ZoomOut();
        @Override public Viewing parent() { return Viewing.INSTANCE; }
    }

    /** All of it, fitted to its box. */
    public record Fit() implements WithAction<Viewing> {
        public static final Fit INSTANCE = new Fit();
        @Override public Viewing parent() { return Viewing.INSTANCE; }
    }

    // ── Arranging — how its room is shared ────────────────────────────────

    /** How its room is shared among what it holds. */
    public record Arranging() implements L1_ControlBranch<ControlRoot> {
        public static final Arranging INSTANCE = new Arranging();
        @Override public ControlRoot parent() { return ControlRoot.INSTANCE; }
    }

    /** A room split in two, beside itself. */
    public record Split() implements WithAction<Arranging> {
        public static final Split INSTANCE = new Split();
        @Override public Arranging parent() { return Arranging.INSTANCE; }
    }

    /** The room shared evenly again. */
    public record EvenOut() implements WithAction<Arranging> {
        public static final EvenOut INSTANCE = new EvenOut();
        @Override public Arranging parent() { return Arranging.INSTANCE; }
    }

    /** A room taken away, its room given to the one beside it - the last never. */
    public record Remove() implements WithAction<Arranging> {
        public static final Remove INSTANCE = new Remove();
        @Override public Arranging parent() { return Arranging.INSTANCE; }
        @Override public String label() { return "Remove one"; }
    }

    /** The rooms as they were at the start. */
    public record ResetLayout() implements WithAction<Arranging> {
        public static final ResetLayout INSTANCE = new ResetLayout();
        @Override public Arranging parent() { return Arranging.INSTANCE; }
        @Override public String label() { return "Reset the layout"; }
    }

    // ── Placing — where it stands, and how big ────────────────────────────

    /** Where it stands in the box it floats in, and how big. */
    public record Placing() implements L1_ControlBranch<ControlRoot> {
        public static final Placing INSTANCE = new Placing();
        @Override public ControlRoot parent() { return ControlRoot.INSTANCE; }
    }

    /** Moved, by call. */
    public record Move() implements WithAction<Placing> {
        public static final Move INSTANCE = new Move();
        @Override public Placing parent() { return Placing.INSTANCE; }
    }

    /** Sized, by call. */
    public record Resize() implements WithAction<Placing> {
        public static final Resize INSTANCE = new Resize();
        @Override public Placing parent() { return Placing.INSTANCE; }
    }

    /** Ringed, by call: the one the keys are with. */
    public record Ring() implements WithAction<Placing> {
        public static final Ring INSTANCE = new Ring();
        @Override public Placing parent() { return Placing.INSTANCE; }
    }

    // ── Content — what it shows ───────────────────────────────────────────

    /** What it shows, changed. */
    public record Content() implements L1_ControlBranch<ControlRoot> {
        public static final Content INSTANCE = new Content();
        @Override public ControlRoot parent() { return ControlRoot.INSTANCE; }
    }

    /** The next of what it can show. */
    public record Next() implements WithAction<Content> {
        public static final Next INSTANCE = new Next();
        @Override public Content parent() { return Content.INSTANCE; }
    }

    /** Nothing shown. */
    public record Clear() implements WithAction<Content> {
        public static final Clear INSTANCE = new Clear();
        @Override public Content parent() { return Content.INSTANCE; }
    }

    /** Something it lacks, so what it shows in its place is seen. */
    public record Missing() implements WithAction<Content> {
        public static final Missing INSTANCE = new Missing();
        @Override public Content parent() { return Content.INSTANCE; }
        @Override public String label() { return "One it lacks"; }
    }

    // ── Asking — what it shows now, in words ──────────────────────────────

    /** What it shows now, asked, and answered in words. */
    public record Asking() implements L1_ControlBranch<ControlRoot> {
        public static final Asking INSTANCE = new Asking();
        @Override public ControlRoot parent() { return ControlRoot.INSTANCE; }
    }

    /** What it shows now. */
    public record Ask() implements WithQuestion<Asking> {
        public static final Ask INSTANCE = new Ask();
        @Override public Asking parent() { return Asking.INSTANCE; }
        @Override public String label() { return "What does it show?"; }
    }
}
