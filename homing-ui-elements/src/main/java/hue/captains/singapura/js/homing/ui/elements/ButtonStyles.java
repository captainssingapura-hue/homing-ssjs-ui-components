package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * The buttons' classes: what every button is, each leaf's own, and the icon button's. What a button looks like is its
 * look's; the bodies hold layout and nothing that could be a value.
 */
public record ButtonStyles() implements CssGroup<ButtonStyles> {

    public static final ButtonStyles INSTANCE = new ButtonStyles();

    /**
     * What every button is: the base button — {@code Control.Button.Base}, the
     * control that completes a task — interactive, labelled. Its rule carries
     * the ring on focus and leaves the edge to the colour word; its density
     * (inset, gap, least width) and its label's type are the design's, and
     * grow with the element's size.
     */
    public record el_button() implements CssClass<ButtonStyles> {
        @Override public String body() { return """
            font: inherit;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            """;
        }
    }

    /**
     * The primary button: the accent surface, the ink for it, its edge — a
     * semantic surface complete, every visual surface of it scaled together by
     * the element's extent: at 1 the word as bound, at 0 the design's neutral,
     * at −1 the meaning turned the other way. Each design anchors each.
     */
    public record el_button_primary() implements CssClass<ButtonStyles> {
        @Override public String body() { return ""; }
    }

    /** The secondary button: the secondary surface, the ink for it, its edge; scaled as the primary is. */
    public record el_button_secondary() implements CssClass<ButtonStyles> {
        @Override public String body() { return ""; }
    }

    /** The danger button; at −1 the design's safety, whatever that looks like there. */
    public record el_button_danger() implements CssClass<ButtonStyles> {
        @Override public String body() { return ""; }
    }

    /** The warning button; at −1 the design's calm. */
    public record el_button_warning() implements CssClass<ButtonStyles> {
        @Override public String body() { return ""; }
    }

    /** The success button; at −1 the design's failure. */
    public record el_button_success() implements CssClass<ButtonStyles> {
        @Override public String body() { return ""; }
    }

    /** The plain button: raised, in body ink. Not a semantic surface; it does not scale. */
    public record el_button_plain() implements CssClass<ButtonStyles> {
        @Override public String body() { return ""; }
    }

    /**
     * A button that is on: what the design does to an interactive thing on
     * hover and on press — a lift and a shadow, a shift, a glow, a wobble —
     * worn only while on, so an off button neither lifts nor presses.
     */
    public record el_button_on() implements CssClass<ButtonStyles> {
        @Override public String body() { return ""; }
    }

    /** A button that is off: inert, and says so. Toggled beside {@code disabled}, in place of {@link el_button_on}. */
    public record el_button_off() implements CssClass<ButtonStyles> {
        @Override public String body() { return ""; }
    }

    /**
     * An icon button's face: the icon it holds, centred in its box. The icon is a
     * grid item there, so its own drop for sitting in a line of text - its
     * vertical-align - does not apply. Layout only: the button's look is its casting's.
     */
    public record el_icon_button() implements CssClass<ButtonStyles> {
        @Override public String body() { return """
            display: inline-grid;
            place-items: center;
            """;
        }
    }

    @Override
    public List<CssClass<ButtonStyles>> cssClasses() {
        return List.of(new el_button(), new el_button_primary(), new el_button_secondary(), new el_button_danger(), new el_button_warning(),
                       new el_button_success(), new el_button_plain(), new el_button_on(), new el_button_off(), new el_icon_button());
    }
}
