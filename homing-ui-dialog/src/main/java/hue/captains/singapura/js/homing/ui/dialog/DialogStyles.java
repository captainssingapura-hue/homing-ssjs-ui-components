package hue.captains.singapura.js.homing.ui.dialog;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import java.util.List;

import static hue.captains.singapura.js.homing.design.DesignClass.of;

/**
 * The dialog's own styles — what a dialog adds to the floating pane it is
 * built on: the scrim that owns the page behind a modal one, the layer over
 * the viewport that is its desk of one, the float that takes the hand
 * through that layer, and the action foot. The frame, the head, the body and
 * the grip are the pane's.
 */
public record DialogStyles() implements CssGroup<DialogStyles> {

    public static final DialogStyles INSTANCE = new DialogStyles();

    /** Over everything but the frame; the filter is the overlay's word. */
    public record dl_scrim() implements CssClass<DialogStyles> {
        @Override public String body() { return """
            position: fixed;
            inset: 0;
            z-index: 10010;
            """;
        }
    }

    /** The layer the dialog floats in: the viewport, as a desk of one; the hand passes through it to the page unless it lands on the float. */
    public record dl_layer() implements CssClass<DialogStyles> {
        @Override public String body() { return """
            position: fixed;
            inset: 0;
            z-index: 10011;
            pointer-events: none;
            """;
        }
    }

    /** The dialog's pane in the layer: the hand lands on it. */
    public record dl_float() implements CssClass<DialogStyles> {
        @Override public String body() { return "pointer-events: auto;"; }
    }

    /** The action foot, its buttons to the right; its look - a row of actions, raised and capped off - is the house's. */
    public record dl_actions() implements CssClass<DialogStyles> {
        @Override public String body() { return """
            flex: 0 0 auto;
            display: flex;
            align-items: center;
            justify-content: flex-end;
            """;
        }
    }

    @Override
    public List<CssClass<DialogStyles>> cssClasses() {
        return List.of(new dl_scrim(), new dl_layer(), new dl_float(), new dl_actions());
    }
}
