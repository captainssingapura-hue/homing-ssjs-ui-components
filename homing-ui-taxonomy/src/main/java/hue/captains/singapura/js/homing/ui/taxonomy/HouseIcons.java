package hue.captains.singapura.js.homing.ui.taxonomy;

/**
 * The house's icons (RFC 0067, D52, D56): a leaf of the Icon branch for each meaning the house shows - each a component,
 * its meaning its own, the same in every style. It is no content: how it is drawn - a character, or an SVG (rotate and
 * flip, the samples) - is each style's icon palette's, a palette drawing what it wants and Base's every one (D26); the
 * look is the Icon branch's, the same for every icon, in the palette's colours. A picture of another shape is another
 * icon. An owner names the icons it may show, or the Icon branch for any the instance registers, and which one it shows
 * is chosen once, as it is made; one not shown is not mounted. The roles an icon plays place it, and its look is the
 * same whatever the role. Defined here beside the house's other components (D40): the house's fifteen listed by
 * homing-ui-icons's module, the panes' five by homing-ui-panes's.
 */
public final class HouseIcons {

    private HouseIcons() {}

    // ── the house's: what a control does, how a thing is, more behind it, what it is measured along ──

    /** One more: a new tab, a new row, a new note. */
    public record AddIcon() implements IconLeaf {
        public static final AddIcon INSTANCE = new AddIcon();
    }

    /** One fewer: this one taken away. */
    public record RemoveIcon() implements IconLeaf {
        public static final RemoveIcon INSTANCE = new RemoveIcon();
    }

    /** Close it: a tab, a pane, a dialog - each closed by the same picture, drawn once in each style. */
    public record CloseIcon() implements IconLeaf {
        public static final CloseIcon INSTANCE = new CloseIcon();
    }

    /** Back to the start: what was changed set as it was. */
    public record ResetIcon() implements IconLeaf {
        public static final ResetIcon INSTANCE = new ResetIcon();
    }

    /** Turn it a step. */
    public record RotateIcon() implements IconLeaf {
        public static final RotateIcon INSTANCE = new RotateIcon();
    }

    /** Mirror it. */
    public record FlipIcon() implements IconLeaf {
        public static final FlipIcon INSTANCE = new FlipIcon();
    }

    /** Keep it where it is: it stays when others move or go. */
    public record PinIcon() implements IconLeaf {
        public static final PinIcon INSTANCE = new PinIcon();
    }

    /** This one is chosen, on, done. */
    public record CheckIcon() implements IconLeaf {
        public static final CheckIcon INSTANCE = new CheckIcon();
    }

    /** Take hold of it: the mark on a knob, a handle, a thing the hand moves. */
    public record GripIcon() implements IconLeaf {
        public static final GripIcon INSTANCE = new GripIcon();
    }

    /** There is more beneath: a row that opens its children, a node that expands. */
    public record DiscloseIcon() implements IconLeaf {
        public static final DiscloseIcon INSTANCE = new DiscloseIcon();
    }

    /** The settings behind it: what it is set by, to look at and change. */
    public record SettingsIcon() implements IconLeaf {
        public static final SettingsIcon INSTANCE = new SettingsIcon();
    }

    /** Bigger or smaller: the size axis. */
    public record SizeIcon() implements IconLeaf {
        public static final SizeIcon INSTANCE = new SizeIcon();
    }

    /** Wider or taller: the aspect axis. */
    public record AspectIcon() implements IconLeaf {
        public static final AspectIcon INSTANCE = new AspectIcon();
    }

    /** More or less of what it means: the extent axis. */
    public record ExtentIcon() implements IconLeaf {
        public static final ExtentIcon INSTANCE = new ExtentIcon();
    }

    /** How much of it: a level, a volume, a gain. */
    public record LevelIcon() implements IconLeaf {
        public static final LevelIcon INSTANCE = new LevelIcon();
    }

    // ── the panes': how a room is parted among its panes, and where the work is ──

    /** Columns: the room parted side by side, and a new one beside what is here. */
    public record ColumnIcon() implements IconLeaf {
        public static final ColumnIcon INSTANCE = new ColumnIcon();
    }

    /** Rows: the room parted one over another, and a new one under what is here. */
    public record RowIcon() implements IconLeaf {
        public static final RowIcon INSTANCE = new RowIcon();
    }

    /** Two rooms become one: this one goes, and its room is given away. */
    public record MergeIcon() implements IconLeaf {
        public static final MergeIcon INSTANCE = new MergeIcon();
    }

    /** Take it out: a tab from its pane, to float on its own. */
    public record DetachIcon() implements IconLeaf {
        public static final DetachIcon INSTANCE = new DetachIcon();
    }

    /** You are in here: the work is going on inside this one, not on the bar that names it. */
    public record WithinIcon() implements IconLeaf {
        public static final WithinIcon INSTANCE = new WithinIcon();
    }
}
