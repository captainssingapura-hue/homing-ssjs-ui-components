package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseKinds.Text;

/**
 * The house's text: independent components of words, each a role's base wherever a component
 * shows words - a card's title is played by a {@link Heading}, a slider's label by a
 * {@link Label}.
 */
public final class HouseText {

    private HouseText() {}

    /** A control's or a field's name. */
    public record Label() implements Component<Text> {
        public static final Label INSTANCE = new Label();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** What a container is called. */
    public record Heading() implements Component<Text> {
        public static final Heading INSTANCE = new Heading();
        @Override public Text parent() { return Text.INSTANCE; }
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
}
