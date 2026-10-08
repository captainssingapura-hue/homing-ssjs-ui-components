package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.L1_RoleBranch;
import hue.captains.singapura.js.homing.component.taxonomy.L2_RoleBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;

/**
 * The house's role catalogue, its second top branch: what a part lets the user do to its owner -
 * choose, commit, change its value, change how it is seen, manage what it holds, go elsewhere.
 * Twenty-four roles under six branches.
 */
public final class HouseDoing {

    private HouseDoing() {}

    /** What a part lets the user do to its owner. */
    public record Doing() implements L1_RoleBranch<RoleRoot> {
        public static final Doing INSTANCE = new Doing();
        @Override public RoleRoot parent() { return RoleRoot.INSTANCE; }
    }

    // ── Choosing — offers what to pick ───────────────────────────────────

    /** Offers what to pick. */
    public record Choosing() implements L2_RoleBranch<Doing> {
        public static final Choosing INSTANCE = new Choosing();
        @Override public Doing parent() { return Doing.INSTANCE; }
    }

    /** One thing offered to pick. */
    public record Choice() implements Role<Choosing> { public static final Choice INSTANCE = new Choice(); @Override public Choosing parent() { return Choosing.INSTANCE; } }
    /** The theme's design style, to pick. */
    public record Style() implements Role<Choosing> { public static final Style INSTANCE = new Style(); @Override public Choosing parent() { return Choosing.INSTANCE; } }
    /** The theme's colours, to pick. */
    public record Palette() implements Role<Choosing> { public static final Palette INSTANCE = new Palette(); @Override public Choosing parent() { return Choosing.INSTANCE; } }
    /** What to open. */
    public record What() implements Role<Choosing> { public static final What INSTANCE = new What(); @Override public Choosing parent() { return Choosing.INSTANCE; } }
    /** How to open it. */
    public record How() implements Role<Choosing> { public static final How INSTANCE = new How(); @Override public Choosing parent() { return Choosing.INSTANCE; } }
    /** In which pane. */
    public record Where() implements Role<Choosing> { public static final Where INSTANCE = new Where(); @Override public Choosing parent() { return Choosing.INSTANCE; } }
    /** Asks which kind of tab to open. */
    public record Picker() implements Role<Choosing> { public static final Picker INSTANCE = new Picker(); @Override public Choosing parent() { return Choosing.INSTANCE; } }

    // ── Committing — ends a decision ─────────────────────────────────────

    /** Ends a decision. */
    public record Committing() implements L2_RoleBranch<Doing> {
        public static final Committing INSTANCE = new Committing();
        @Override public Doing parent() { return Doing.INSTANCE; }
    }

    /** Does what was chosen. */
    public record Go() implements Role<Committing> { public static final Go INSTANCE = new Go(); @Override public Committing parent() { return Committing.INSTANCE; } }
    /** Leaves without choosing. */
    public record Cancel() implements Role<Committing> { public static final Cancel INSTANCE = new Cancel(); @Override public Committing parent() { return Committing.INSTANCE; } }
    /** One action the owner offers. */
    public record Action() implements Role<Committing> { public static final Action INSTANCE = new Action(); @Override public Committing parent() { return Committing.INSTANCE; } }

    // ── Changing — changes the owner's value ─────────────────────────────

    /** Changes the owner's value. */
    public record Changing() implements L2_RoleBranch<Doing> {
        public static final Changing INSTANCE = new Changing();
        @Override public Doing parent() { return Doing.INSTANCE; }
    }

    /** Flips the owner's setting. */
    public record Toggle() implements Role<Changing> { public static final Toggle INSTANCE = new Toggle(); @Override public Changing parent() { return Changing.INSTANCE; } }
    /** Sets the owner's number along a scale. */
    public record Adjust() implements Role<Changing> { public static final Adjust INSTANCE = new Adjust(); @Override public Changing parent() { return Changing.INSTANCE; } }
    /** Takes the hand to set the value. */
    public record Thumb() implements Role<Changing> { public static final Thumb INSTANCE = new Thumb(); @Override public Changing parent() { return Changing.INSTANCE; } }
    /** Puts the owner's value back to its default. */
    public record Reset() implements Role<Changing> { public static final Reset INSTANCE = new Reset(); @Override public Changing parent() { return Changing.INSTANCE; } }

    // ── Viewing — changes how the owner is seen ──────────────────────────

    /** Changes how the owner is seen. */
    public record Viewing() implements L2_RoleBranch<Doing> {
        public static final Viewing INSTANCE = new Viewing();
        @Override public Doing parent() { return Doing.INSTANCE; }
    }

    /** Brings the owner closer. */
    public record ZoomIn() implements Role<Viewing> { public static final ZoomIn INSTANCE = new ZoomIn(); @Override public Viewing parent() { return Viewing.INSTANCE; } }
    /** Takes the owner further away. */
    public record ZoomOut() implements Role<Viewing> { public static final ZoomOut INSTANCE = new ZoomOut(); @Override public Viewing parent() { return Viewing.INSTANCE; } }
    /** Fits the owner to its room again. */
    public record Fit() implements Role<Viewing> { public static final Fit INSTANCE = new Fit(); @Override public Viewing parent() { return Viewing.INSTANCE; } }

    // ── Managing — changes what the owner holds, or whether it stays ─────

    /** Changes what the owner holds, or whether it stays. */
    public record Managing() implements L2_RoleBranch<Doing> {
        public static final Managing INSTANCE = new Managing();
        @Override public Doing parent() { return Doing.INSTANCE; }
    }

    /** Closes the owner, or what it stands for. */
    public record Close() implements Role<Managing> { public static final Close INSTANCE = new Close(); @Override public Managing parent() { return Managing.INSTANCE; } }
    /** Adds a member. */
    public record Add() implements Role<Managing> { public static final Add INSTANCE = new Add(); @Override public Managing parent() { return Managing.INSTANCE; } }

    // ── Going — takes the user elsewhere ─────────────────────────────────

    /** Takes the user elsewhere. */
    public record Going() implements L2_RoleBranch<Doing> {
        public static final Going INSTANCE = new Going();
        @Override public Doing parent() { return Doing.INSTANCE; }
    }

    /** Goes to what the owner stands for. */
    public record Open() implements Role<Going> { public static final Open INSTANCE = new Open(); @Override public Going parent() { return Going.INSTANCE; } }
    /** Goes to the site's home. */
    public record Home() implements Role<Going> { public static final Home INSTANCE = new Home(); @Override public Going parent() { return Going.INSTANCE; } }
    /** Opens the site's preferences. */
    public record Preferences() implements Role<Going> { public static final Preferences INSTANCE = new Preferences(); @Override public Going parent() { return Going.INSTANCE; } }
    /** Shows the way back. */
    public record Path() implements Role<Going> { public static final Path INSTANCE = new Path(); @Override public Going parent() { return Going.INSTANCE; } }
    /** One step of the way back. */
    public record Step() implements Role<Going> { public static final Step INSTANCE = new Step(); @Override public Going parent() { return Going.INSTANCE; } }
}
