package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Text;

/**
 * The house's text: independent components of words, each a role's base wherever a component
 * shows words - a card's title is played by a {@link Heading4}, a slider's label by a
 * {@link Label}.
 */
public final class HouseText {

    private HouseText() {}

    /** A control's or a field's name. */
    public record Label() implements Component<Text> {
        public static final Label INSTANCE = new Label();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    // ── the headings: one leaf for each level of a page's outline, under the Heading branch ──

    /** A page's own name: the first heading of its outline. */
    public record Heading1() implements Component<HouseBranches.Heading> {
        public static final Heading1 INSTANCE = new Heading1();
        @Override public HouseBranches.Heading parent() { return HouseBranches.Heading.INSTANCE; }
    }

    /** The heading of one of a page's great divisions. */
    public record Heading2() implements Component<HouseBranches.Heading> {
        public static final Heading2 INSTANCE = new Heading2();
        @Override public HouseBranches.Heading parent() { return HouseBranches.Heading.INSTANCE; }
    }

    /** The heading of a section within a part. */
    public record Heading3() implements Component<HouseBranches.Heading> {
        public static final Heading3 INSTANCE = new Heading3();
        @Override public HouseBranches.Heading parent() { return HouseBranches.Heading.INSTANCE; }
    }

    /** The heading of a smaller section, or of what a container holds: a card's, a group's, a setting's title. */
    public record Heading4() implements Component<HouseBranches.Heading> {
        public static final Heading4 INSTANCE = new Heading4();
        @Override public HouseBranches.Heading parent() { return HouseBranches.Heading.INSTANCE; }
    }

    /** The heading of a division within a smaller section. */
    public record Heading5() implements Component<HouseBranches.Heading> {
        public static final Heading5 INSTANCE = new Heading5();
        @Override public HouseBranches.Heading parent() { return HouseBranches.Heading.INSTANCE; }
    }

    /** The least heading: a division within that. */
    public record Heading6() implements Component<HouseBranches.Heading> {
        public static final Heading6 INSTANCE = new Heading6();
        @Override public HouseBranches.Heading parent() { return HouseBranches.Heading.INSTANCE; }
    }

    /** The small line above a heading. */
    public record Kicker() implements Component<Text> {
        public static final Kicker INSTANCE = new Kicker();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** Quiet words beside something: a note, a hint. */
    public record Caption() implements Component<Text> {
        public static final Caption INSTANCE = new Caption();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** Words said with weight among the words round them: a table's heads, a cell said strongly. */
    public record Strong() implements Component<Text> {
        public static final Strong INSTANCE = new Strong();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** Words said under the words round them, in the faded ink: a cell that matters less. */
    public record Muted() implements Component<Text> {
        public static final Muted INSTANCE = new Muted();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** The first words of a body, or its summary. */
    public record Lede() implements Component<Text> {
        public static final Lede INSTANCE = new Lede();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** A value read out: a number, a percentage. */
    public record Readout() implements Component<Text> {
        public static final Readout INSTANCE = new Readout();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** A table cell's words: plain text, set in its cell - a fact where a row and a column meet, or the name heading a column (D42). */
    public record TextCell() implements Component<Text> {
        public static final TextCell INSTANCE = new TextCell();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** A short tag beside a heading. */
    public record Badge() implements Component<Text> {
        public static final Badge INSTANCE = new Badge();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** A count in a rounded tag. */
    public record Pill() implements Component<Text> {
        public static final Pill INSTANCE = new Pill();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** A brand's name, set as its mark. */
    public record Wordmark() implements Component<Text> {
        public static final Wordmark INSTANCE = new Wordmark();
        @Override public Text parent() { return Text.INSTANCE; }
    }
    // ── code, and the badges that say how a thing stands ──

    /** Words that are code - a name, a value, a command - set apart from the words round them. */
    public record Code() implements Component<Text> {
        public static final Code INSTANCE = new Code();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** A short word saying a thing went well: done, passed, resolved. */
    public record SuccessBadge() implements Component<Text> {
        public static final SuccessBadge INSTANCE = new SuccessBadge();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** A short word saying a thing needs a look: in progress, pending, unplaced. */
    public record WarningBadge() implements Component<Text> {
        public static final WarningBadge INSTANCE = new WarningBadge();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** A short word saying a thing went wrong: failed, blocked, broken. */
    public record DangerBadge() implements Component<Text> {
        public static final DangerBadge INSTANCE = new DangerBadge();
        @Override public Text parent() { return Text.INSTANCE; }
    }

}
