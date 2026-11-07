package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * The classes of the elements with no group of their own: a text leaf, the browser's checkbox and a pane thumbnail's name.
 * Every colour, face, edge, corner and motion is the look's; the bodies hold layout and nothing that could be a value.
 */
public record ElementStyles() implements CssGroup<ElementStyles> {

    public static final ElementStyles INSTANCE = new ElementStyles();

    /** A text leaf's element: no margin of its own - where it sits, and the room round it, are its owner's. Layout only. */
    public record el_text() implements CssClass<ElementStyles> {
        @Override public String body() { return "margin: 0;\n"; }
    }

    /**
     * A checkbox the browser draws, its mark in the colour its look gives it (the accent): an owner realizing the house's
     * checkbox on a native one wears this beside its look, as a range does. No margin of its own: where it sits is its owner's.
     */
    public record el_checkbox() implements CssClass<ElementStyles> {
        @Override public String body() { return "accent-color: currentColor;\nmargin: 0;\n"; }
    }

    /** A thumbnail's name: on one line, cut short where the picture is small. */
    public record el_pane_thumb_name() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            max-width: 100%;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            """;
        }
    }

    @Override
    public List<CssClass<ElementStyles>> cssClasses() {
        return List.of(new el_text(), new el_checkbox(), new el_pane_thumb_name());
    }
}
