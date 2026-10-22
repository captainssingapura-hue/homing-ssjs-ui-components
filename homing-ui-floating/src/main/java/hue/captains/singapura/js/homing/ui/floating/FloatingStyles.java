package hue.captains.singapura.js.homing.ui.floating;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;

import java.util.List;
import java.util.Set;


/**
 * The floating pane's styles: the desk it floats on, the frame, the head that
 * moves it, the body that holds what it shows, the grip that sizes it. The
 * frame is {@code Container.Pane.Floating}; its place and its measure are its
 * user's, carried in runtime variables the pane sets, never the design's.
 * Every look is a design word; the bodies hold layout only.
 */
public record FloatingStyles() implements CssGroup<FloatingStyles> {

    public static final FloatingStyles INSTANCE = new FloatingStyles();

    /** The desk: a recessed floor filling its host (a flex box), where the panes are placed. */
    public record fp_desk() implements CssClass<FloatingStyles> {
        @Override public String body() { return """
            position: relative;
            flex: 1 1 auto;
            align-self: stretch;
            min-width: 0;
            min-height: 0;
            overflow: hidden;
            """;
        }
    }

    /** The desk as a layer over docks: it lies over its positioned host, and the hand passes through it except on a pane. */
    public record fp_desk_layer() implements CssClass<FloatingStyles> {
        @Override public String body() { return """
            position: absolute;
            inset: 0;
            z-index: 1;
            pointer-events: none;
            overflow: hidden;
            """;
        }
    }

    @Override
    public List<CssClass<FloatingStyles>> cssClasses() {
        return List.of(new fp_desk(), new fp_desk_layer());   // the frame, the head and the body the panes', with the floating pane (23 Oct)
    }
}
