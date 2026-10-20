package hue.captains.singapura.js.homing.ui.taxonomy;

/**
 * The house's icons (RFC 0067, D52, D56): a leaf of the Icon branch for each picture the house draws - each a component,
 * saying what it shows, its content, the same in every style: a glyph, or an SVG (rotate and flip, the samples). How it is
 * drawn is each style's icon palette's, a palette drawing what it wants and Base's every one (D26); the look is the Icon
 * branch's, the same for every icon, in the palette's colours. A variation of one picture is a state of its icon; a
 * picture of another shape is another icon. An owner names the icons it may show, or the Icon branch for any the
 * instance registers; the roles an icon plays place it, and its look is the same whatever the role. Defined here beside the house's other components (D40): the house's
 * fifteen listed by homing-ui-icons's module, the panes' five by homing-ui-panes's.
 */
public final class HouseIcons {

    private HouseIcons() {}

    // ── the house's: what a control does, how a thing is, more behind it, what it is measured along ──

    /** One more: a new tab, a new row, a new note. */
    public record AddIcon() implements IconLeaf {
        public static final AddIcon INSTANCE = new AddIcon();
        @Override public IconContent content() { return new IconContent.Glyph("add"); }
    }

    /** One fewer: this one taken away. */
    public record RemoveIcon() implements IconLeaf {
        public static final RemoveIcon INSTANCE = new RemoveIcon();
        @Override public IconContent content() { return new IconContent.Glyph("remove"); }
    }

    /** Close it: a tab, a pane, a dialog - each closed by the same picture, drawn once in each style. */
    public record CloseIcon() implements IconLeaf {
        public static final CloseIcon INSTANCE = new CloseIcon();
        @Override public IconContent content() { return new IconContent.Glyph("close"); }
    }

    /** Back to the start: what was changed set as it was. */
    public record ResetIcon() implements IconLeaf {
        public static final ResetIcon INSTANCE = new ResetIcon();
        @Override public IconContent content() { return new IconContent.Glyph("reset"); }
    }

    /** Turn it a step. */
    public record RotateIcon() implements IconLeaf {
        public static final RotateIcon INSTANCE = new RotateIcon();
        @Override public IconContent content() { return new IconContent.Svg("rotate"); }
    }

    /** Mirror it. */
    public record FlipIcon() implements IconLeaf {
        public static final FlipIcon INSTANCE = new FlipIcon();
        @Override public IconContent content() { return new IconContent.Svg("flip"); }
    }

    /** Keep it where it is: it stays when others move or go. */
    public record PinIcon() implements IconLeaf {
        public static final PinIcon INSTANCE = new PinIcon();
        @Override public IconContent content() { return new IconContent.Glyph("pin"); }
    }

    /** This one is chosen, on, done. */
    public record CheckIcon() implements IconLeaf {
        public static final CheckIcon INSTANCE = new CheckIcon();
        @Override public IconContent content() { return new IconContent.Glyph("check"); }
    }

    /** Take hold of it: the mark on a knob, a handle, a thing the hand moves. */
    public record GripIcon() implements IconLeaf {
        public static final GripIcon INSTANCE = new GripIcon();
        @Override public IconContent content() { return new IconContent.Glyph("grip"); }
    }

    /** There is more beneath: a row that opens its children, a node that expands. */
    public record DiscloseIcon() implements IconLeaf {
        public static final DiscloseIcon INSTANCE = new DiscloseIcon();
        @Override public IconContent content() { return new IconContent.Glyph("disclose"); }
    }

    /** The settings behind it: what it is set by, to look at and change. */
    public record SettingsIcon() implements IconLeaf {
        public static final SettingsIcon INSTANCE = new SettingsIcon();
        @Override public IconContent content() { return new IconContent.Glyph("settings"); }
    }

    /** Bigger or smaller: the size axis. */
    public record SizeIcon() implements IconLeaf {
        public static final SizeIcon INSTANCE = new SizeIcon();
        @Override public IconContent content() { return new IconContent.Glyph("size"); }
    }

    /** Wider or taller: the aspect axis. */
    public record AspectIcon() implements IconLeaf {
        public static final AspectIcon INSTANCE = new AspectIcon();
        @Override public IconContent content() { return new IconContent.Glyph("aspect"); }
    }

    /** More or less of what it means: the extent axis. */
    public record ExtentIcon() implements IconLeaf {
        public static final ExtentIcon INSTANCE = new ExtentIcon();
        @Override public IconContent content() { return new IconContent.Glyph("extent"); }
    }

    /** How much of it: a level, a volume, a gain. */
    public record LevelIcon() implements IconLeaf {
        public static final LevelIcon INSTANCE = new LevelIcon();
        @Override public IconContent content() { return new IconContent.Glyph("level"); }
    }

    // ── the panes': how a room is parted among its panes, and where the work is ──

    /** Columns: the room parted side by side, and a new one beside what is here. */
    public record ColumnIcon() implements IconLeaf {
        public static final ColumnIcon INSTANCE = new ColumnIcon();
        @Override public IconContent content() { return new IconContent.Glyph("column"); }
    }

    /** Rows: the room parted one over another, and a new one under what is here. */
    public record RowIcon() implements IconLeaf {
        public static final RowIcon INSTANCE = new RowIcon();
        @Override public IconContent content() { return new IconContent.Glyph("row"); }
    }

    /** Two rooms become one: this one goes, and its room is given away. */
    public record MergeIcon() implements IconLeaf {
        public static final MergeIcon INSTANCE = new MergeIcon();
        @Override public IconContent content() { return new IconContent.Glyph("merge"); }
    }

    /** Take it out: a tab from its pane, to float on its own. */
    public record DetachIcon() implements IconLeaf {
        public static final DetachIcon INSTANCE = new DetachIcon();
        @Override public IconContent content() { return new IconContent.Glyph("detach"); }
    }

    /** You are in here: the work is going on inside this one, not on the bar that names it. */
    public record WithinIcon() implements IconLeaf {
        public static final WithinIcon INSTANCE = new WithinIcon();
        @Override public IconContent content() { return new IconContent.Glyph("within"); }
    }
}
