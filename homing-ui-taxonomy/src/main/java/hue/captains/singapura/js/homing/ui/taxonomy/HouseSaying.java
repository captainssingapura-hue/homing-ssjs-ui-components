package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.L1_RoleBranch;
import hue.captains.singapura.js.homing.component.taxonomy.L2_RoleBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;

/**
 * The house's role catalogue, its first top branch: what a part tells about its owner - what it
 * is, more about it, how to use it, how it is now, a state shown by a mark, the marks of a scale.
 * Twenty-one roles under six branches.
 */
public final class HouseSaying {

    private HouseSaying() {}

    /** What a part tells about its owner. */
    public record Saying() implements L1_RoleBranch<RoleRoot> {
        public static final Saying INSTANCE = new Saying();
        @Override public RoleRoot parent() { return RoleRoot.INSTANCE; }
    }

    // ── Naming — says what its owner is ──────────────────────────────────

    /** Says what its owner is. */
    public record Naming() implements L2_RoleBranch<Saying> {
        public static final Naming INSTANCE = new Naming();
        @Override public Saying parent() { return Saying.INSTANCE; }
    }

    /** Names a container: a card, a panel, a pane, a group, a setting. */
    public record Title() implements Role<Naming> { public static final Title INSTANCE = new Title(); @Override public Naming parent() { return Naming.INSTANCE; } }
    /** Names one section of the owner. */
    public record Subtitle() implements Role<Naming> { public static final Subtitle INSTANCE = new Subtitle(); @Override public Naming parent() { return Naming.INSTANCE; } }
    /** Names a control or an item. */
    public record Name() implements Role<Naming> { public static final Name INSTANCE = new Name(); @Override public Naming parent() { return Naming.INSTANCE; } }
    /** Says what sort of thing the owner is. */
    public record Category() implements Role<Naming> { public static final Category INSTANCE = new Category(); @Override public Naming parent() { return Naming.INSTANCE; } }
    /** Shows a glyph that stands for the owner. */
    public record Symbol() implements Role<Naming> { public static final Symbol INSTANCE = new Symbol(); @Override public Naming parent() { return Naming.INSTANCE; } }

    // ── Describing — says more about it ──────────────────────────────────

    /** Says more about its owner. */
    public record Describing() implements L2_RoleBranch<Saying> {
        public static final Describing INSTANCE = new Describing();
        @Override public Saying parent() { return Saying.INSTANCE; }
    }

    /** Says briefly what the owner is about. */
    public record Summary() implements Role<Describing> { public static final Summary INSTANCE = new Summary(); @Override public Describing parent() { return Describing.INSTANCE; } }
    /** Remarks on the owner's state: where a value came from, that a pane is empty. */
    public record Note() implements Role<Describing> { public static final Note INSTANCE = new Note(); @Override public Describing parent() { return Describing.INSTANCE; } }
    /** Marks the owner with a short word. */
    public record Tag() implements Role<Describing> { public static final Tag INSTANCE = new Tag(); @Override public Describing parent() { return Describing.INSTANCE; } }

    // ── Guiding — helps the user use it ──────────────────────────────────

    /** Helps the user use its owner. */
    public record Guiding() implements L2_RoleBranch<Saying> {
        public static final Guiding INSTANCE = new Guiding();
        @Override public Saying parent() { return Saying.INSTANCE; }
    }

    /** Tells how to reach the owner faster: a shortcut. */
    public record Hint() implements Role<Guiding> { public static final Hint INSTANCE = new Hint(); @Override public Guiding parent() { return Guiding.INSTANCE; } }
    /** Asks the user what to do. */
    public record Prompt() implements Role<Guiding> { public static final Prompt INSTANCE = new Prompt(); @Override public Guiding parent() { return Guiding.INSTANCE; } }

    // ── Reporting — says how it is now ───────────────────────────────────

    /** Says how its owner is now. */
    public record Reporting() implements L2_RoleBranch<Saying> {
        public static final Reporting INSTANCE = new Reporting();
        @Override public Saying parent() { return Saying.INSTANCE; }
    }

    /** States the owner's value, in words. */
    public record Value() implements Role<Reporting> { public static final Value INSTANCE = new Value(); @Override public Reporting parent() { return Reporting.INSTANCE; } }
    /** Shows how far along the value is. */
    public record Level() implements Role<Reporting> { public static final Level INSTANCE = new Level(); @Override public Reporting parent() { return Reporting.INSTANCE; } }
    /** Says how many the owner holds. */
    public record Count() implements Role<Reporting> { public static final Count INSTANCE = new Count(); @Override public Reporting parent() { return Reporting.INSTANCE; } }
    /** Says how the owner is: loading, failed. */
    public record Status() implements Role<Reporting> { public static final Status INSTANCE = new Status(); @Override public Reporting parent() { return Reporting.INSTANCE; } }

    // ── Signalling — shows a state or a place, by a mark ─────────────────

    /** Shows a state or a place, by a mark rather than by words. */
    public record Signalling() implements L2_RoleBranch<Saying> {
        public static final Signalling INSTANCE = new Signalling();
        @Override public Saying parent() { return Saying.INSTANCE; }
    }

    /** Shows that the keys are inside the owner. */
    public record Keys() implements Role<Signalling> { public static final Keys INSTANCE = new Keys(); @Override public Signalling parent() { return Signalling.INSTANCE; } }
    /** Shows where a dragged thing would land. */
    public record Landing() implements Role<Signalling> { public static final Landing INSTANCE = new Landing(); @Override public Signalling parent() { return Signalling.INSTANCE; } }
    /** Shows that a choice leads further. */
    public record Disclose() implements Role<Signalling> { public static final Disclose INSTANCE = new Disclose(); @Override public Signalling parent() { return Signalling.INSTANCE; } }

    // ── Scaling — marks the owner's scale ────────────────────────────────

    /** Marks the owner's scale. */
    public record Scaling() implements L2_RoleBranch<Saying> {
        public static final Scaling INSTANCE = new Scaling();
        @Override public Saying parent() { return Saying.INSTANCE; }
    }

    /** Shows the whole range. */
    public record Span() implements Role<Scaling> { public static final Span INSTANCE = new Span(); @Override public Scaling parent() { return Scaling.INSTANCE; } }
    /** Marks where the value rests. */
    public record Rest() implements Role<Scaling> { public static final Rest INSTANCE = new Rest(); @Override public Scaling parent() { return Scaling.INSTANCE; } }
    /** Marks a step of the scale. */
    public record Notch() implements Role<Scaling> { public static final Notch INSTANCE = new Notch(); @Override public Scaling parent() { return Scaling.INSTANCE; } }
    /** Numbers a step. */
    public record Figure() implements Role<Scaling> { public static final Figure INSTANCE = new Figure(); @Override public Scaling parent() { return Scaling.INSTANCE; } }
}
