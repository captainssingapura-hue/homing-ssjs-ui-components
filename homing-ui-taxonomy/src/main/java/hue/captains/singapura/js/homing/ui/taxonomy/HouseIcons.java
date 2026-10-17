package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Leaf;

/**
 * The house's icons (RFC 0067, D52): a leaf of the Icon branch for each picture the house draws - each a component, its
 * picture its look, every style's own: each style's look of it draws its glyph, in the palette's colours, and Base draws
 * every one (D26). A variation of one picture is a state of its icon; a picture of another shape is another icon. An
 * owner names the icons it may show, or the Icon branch for any the instance registers; the roles an icon plays place
 * it, and its look is its own whatever the role. Defined here beside the house's other components (D40): the house's
 * fifteen listed by homing-ui-icons's module, the panes' five by homing-ui-panes's.
 */
public final class HouseIcons {

    private HouseIcons() {}

    // ── the house's: what a control does, how a thing is, more behind it, what it is measured along ──

    /** One more: a new tab, a new row, a new note. */
    public record AddIcon() implements Leaf<HouseBranches.Icon> {
        public static final AddIcon INSTANCE = new AddIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** One fewer: this one taken away. */
    public record RemoveIcon() implements Leaf<HouseBranches.Icon> {
        public static final RemoveIcon INSTANCE = new RemoveIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Close it: a tab, a pane, a dialog - each closed by the same picture, drawn once in each style. */
    public record CloseIcon() implements Leaf<HouseBranches.Icon> {
        public static final CloseIcon INSTANCE = new CloseIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Back to the start: what was changed set as it was. */
    public record ResetIcon() implements Leaf<HouseBranches.Icon> {
        public static final ResetIcon INSTANCE = new ResetIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Turn it a step. */
    public record RotateIcon() implements Leaf<HouseBranches.Icon> {
        public static final RotateIcon INSTANCE = new RotateIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Mirror it. */
    public record FlipIcon() implements Leaf<HouseBranches.Icon> {
        public static final FlipIcon INSTANCE = new FlipIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Keep it where it is: it stays when others move or go. */
    public record PinIcon() implements Leaf<HouseBranches.Icon> {
        public static final PinIcon INSTANCE = new PinIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** This one is chosen, on, done. */
    public record CheckIcon() implements Leaf<HouseBranches.Icon> {
        public static final CheckIcon INSTANCE = new CheckIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Take hold of it: the mark on a knob, a handle, a thing the hand moves. */
    public record GripIcon() implements Leaf<HouseBranches.Icon> {
        public static final GripIcon INSTANCE = new GripIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** There is more beneath: a row that opens its children, a node that expands. */
    public record DiscloseIcon() implements Leaf<HouseBranches.Icon> {
        public static final DiscloseIcon INSTANCE = new DiscloseIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** The settings behind it: what it is set by, to look at and change. */
    public record SettingsIcon() implements Leaf<HouseBranches.Icon> {
        public static final SettingsIcon INSTANCE = new SettingsIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Bigger or smaller: the size axis. */
    public record SizeIcon() implements Leaf<HouseBranches.Icon> {
        public static final SizeIcon INSTANCE = new SizeIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Wider or taller: the aspect axis. */
    public record AspectIcon() implements Leaf<HouseBranches.Icon> {
        public static final AspectIcon INSTANCE = new AspectIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** More or less of what it means: the extent axis. */
    public record ExtentIcon() implements Leaf<HouseBranches.Icon> {
        public static final ExtentIcon INSTANCE = new ExtentIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** How much of it: a level, a volume, a gain. */
    public record LevelIcon() implements Leaf<HouseBranches.Icon> {
        public static final LevelIcon INSTANCE = new LevelIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    // ── the panes': how a room is parted among its panes, and where the work is ──

    /** Columns: the room parted side by side, and a new one beside what is here. */
    public record ColumnIcon() implements Leaf<HouseBranches.Icon> {
        public static final ColumnIcon INSTANCE = new ColumnIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Rows: the room parted one over another, and a new one under what is here. */
    public record RowIcon() implements Leaf<HouseBranches.Icon> {
        public static final RowIcon INSTANCE = new RowIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Two rooms become one: this one goes, and its room is given away. */
    public record MergeIcon() implements Leaf<HouseBranches.Icon> {
        public static final MergeIcon INSTANCE = new MergeIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** Take it out: a tab from its pane, to float on its own. */
    public record DetachIcon() implements Leaf<HouseBranches.Icon> {
        public static final DetachIcon INSTANCE = new DetachIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }

    /** You are in here: the work is going on inside this one, not on the bar that names it. */
    public record WithinIcon() implements Leaf<HouseBranches.Icon> {
        public static final WithinIcon INSTANCE = new WithinIcon();
        @Override public HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
    }
}
