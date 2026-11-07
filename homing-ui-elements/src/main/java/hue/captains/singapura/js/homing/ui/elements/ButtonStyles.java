package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * What a plain {@code <button>} element wears where no button component is made: the base button, and the primary's word.
 * A button component wears its look instead. The bodies hold layout and nothing that could be a value.
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

    @Override
    public List<CssClass<ButtonStyles>> cssClasses() {
        return List.of(new el_button(), new el_button_primary());
    }
}
