package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.L1_RoleBranch;
import hue.captains.singapura.js.homing.component.taxonomy.L2_RoleBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;

/**
 * The house's role catalogue, its third top branch: how a part makes up its owner's room - its
 * bands, what it shows over or under itself, what it shows, one of many it holds, what divides
 * them, what the hand takes. Twenty-two roles under six branches.
 */
public final class HouseShaping {

    private HouseShaping() {}

    /** How a part makes up its owner's room. */
    public record Shaping() implements L1_RoleBranch<RoleRoot> {
        public static final Shaping INSTANCE = new Shaping();
        @Override public RoleRoot parent() { return RoleRoot.INSTANCE; }
    }

    // ── Bands — strips of the owner's room ───────────────────────────────

    /** Strips of the owner's room. */
    public record Bands() implements L2_RoleBranch<Shaping> {
        public static final Bands INSTANCE = new Bands();
        @Override public Shaping parent() { return Shaping.INSTANCE; }
    }

    /** The band that names the owner and carries its controls. */
    public record Head() implements Role<Bands> { public static final Head INSTANCE = new Head(); @Override public Bands parent() { return Bands.INSTANCE; } }
    /** The row of the owner's actions. */
    public record Actions() implements Role<Bands> { public static final Actions INSTANCE = new Actions(); @Override public Bands parent() { return Bands.INSTANCE; } }
    /** The bar of the owner's tabs. */
    public record Tabs() implements Role<Bands> { public static final Tabs INSTANCE = new Tabs(); @Override public Bands parent() { return Bands.INSTANCE; } }

    // ── Layers — what the owner shows over or under itself ───────────────

    /** What the owner shows over or under itself. */
    public record Layers() implements L2_RoleBranch<Shaping> {
        public static final Layers INSTANCE = new Layers();
        @Override public Shaping parent() { return Shaping.INSTANCE; }
    }

    /** The window the owner shows in. */
    public record Window() implements Role<Layers> { public static final Window INSTANCE = new Window(); @Override public Layers parent() { return Layers.INSTANCE; } }
    /** Veils what is behind the owner. */
    public record Veil() implements Role<Layers> { public static final Veil INSTANCE = new Veil(); @Override public Layers parent() { return Layers.INSTANCE; } }
    /** A pane floating on the owner. */
    public record Float() implements Role<Layers> { public static final Float INSTANCE = new Float(); @Override public Layers parent() { return Layers.INSTANCE; } }
    /** A menu shown where it was asked. */
    public record Popup() implements Role<Layers> { public static final Popup INSTANCE = new Popup(); @Override public Layers parent() { return Layers.INSTANCE; } }

    // ── Contents — what the owner shows ──────────────────────────────────

    /** What the owner shows. */
    public record Contents() implements L2_RoleBranch<Shaping> {
        public static final Contents INSTANCE = new Contents();
        @Override public Shaping parent() { return Shaping.INSTANCE; }
    }

    /** What the owner's window shows. */
    public record Page() implements Role<Contents> { public static final Page INSTANCE = new Page(); @Override public Contents parent() { return Contents.INSTANCE; } }
    /** Holds the owner's one tab. */
    public record Host() implements Role<Contents> { public static final Host INSTANCE = new Host(); @Override public Contents parent() { return Contents.INSTANCE; } }
    /** The list one picks from. */
    public record Index() implements Role<Contents> { public static final Index INSTANCE = new Index(); @Override public Contents parent() { return Contents.INSTANCE; } }
    /** What the pick shows. */
    public record Detail() implements Role<Contents> { public static final Detail INSTANCE = new Detail(); @Override public Contents parent() { return Contents.INSTANCE; } }
    /** A menu shown still. */
    public record Specimen() implements Role<Contents> { public static final Specimen INSTANCE = new Specimen(); @Override public Contents parent() { return Contents.INSTANCE; } }
    /** The field a setting is shown on: its title, its note, its reset. */
    public record Setting() implements Role<Contents> { public static final Setting INSTANCE = new Setting(); @Override public Contents parent() { return Contents.INSTANCE; } }
    /** Lays the owner's regions out. */
    public record Layout() implements Role<Contents> { public static final Layout INSTANCE = new Layout(); @Override public Contents parent() { return Contents.INSTANCE; } }

    // ── Members — one of many the owner holds ────────────────────────────

    /** One of many the owner holds. */
    public record Members() implements L2_RoleBranch<Shaping> {
        public static final Members INSTANCE = new Members();
        @Override public Shaping parent() { return Shaping.INSTANCE; }
    }

    /** One of the controls the owner gathers. */
    public record Member() implements Role<Members> { public static final Member INSTANCE = new Member(); @Override public Members parent() { return Members.INSTANCE; } }
    /** One of the things the owner lists. */
    public record Entry() implements Role<Members> { public static final Entry INSTANCE = new Entry(); @Override public Members parent() { return Members.INSTANCE; } }
    /** One tabbed pane in one region of the owner. */
    public record Dock() implements Role<Members> { public static final Dock INSTANCE = new Dock(); @Override public Members parent() { return Members.INSTANCE; } }
    /** One region of a grid, drawn. */
    public record Cell() implements Role<Members> { public static final Cell INSTANCE = new Cell(); @Override public Members parent() { return Members.INSTANCE; } }

    // ── Dividers — separate its regions or members ───────────────────────

    /** Separate the owner's regions or members. */
    public record Dividers() implements L2_RoleBranch<Shaping> {
        public static final Dividers INSTANCE = new Dividers();
        @Override public Shaping parent() { return Shaping.INSTANCE; }
    }

    /** The line between two regions, moved by hand. */
    public record Seam() implements Role<Dividers> { public static final Seam INSTANCE = new Seam(); @Override public Dividers parent() { return Dividers.INSTANCE; } }
    /** Marks where a group or a step ends. */
    public record Break() implements Role<Dividers> { public static final Break INSTANCE = new Break(); @Override public Dividers parent() { return Dividers.INSTANCE; } }

    // ── Handles — what the hand or the pointer takes ─────────────────────

    /** What the hand or the pointer takes. */
    public record Handles() implements L2_RoleBranch<Shaping> {
        public static final Handles INSTANCE = new Handles();
        @Override public Shaping parent() { return Shaping.INSTANCE; }
    }

    /** Reveals the owner when the pointer reaches it. */
    public record Reveal() implements Role<Handles> { public static final Reveal INSTANCE = new Reveal(); @Override public Handles parent() { return Handles.INSTANCE; } }
    /** Carries the owner into a strip, to be picked, moved and closed. */
    public record Chip() implements Role<Handles> { public static final Chip INSTANCE = new Chip(); @Override public Handles parent() { return Handles.INSTANCE; } }
}
