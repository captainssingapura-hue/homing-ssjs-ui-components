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

    /**
     * The frame: a raised box above the desk with the overlay's shadow, at the
     * place and measure its user gave it — {@code --fp-x}, {@code --fp-y},
     * {@code --fp-w}, {@code --fp-h} — and on the stack at {@code --fp-z}.
     * Its rule carries the ring on focus; its corner and rule are a container's.
     */
    public record fp_frame() implements CssClass<FloatingStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--fp-x"), new CssVar("--fp-y"), new CssVar("--fp-w"), new CssVar("--fp-h"), new CssVar("--fp-z")); }
        @Override public String body() { return """
            position: absolute;
            left: var(--fp-x);
            top: var(--fp-y);
            inline-size: var(--fp-w);
            block-size: var(--fp-h);
            z-index: var(--fp-z);
            pointer-events: auto;
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            min-width: 0;
            min-height: 0;
            overflow: hidden;
            """;
        }
    }

    /** The head: the inverted bar that names the pane and moves it; a pane's air is its head's. */
    public record fp_head() implements CssClass<FloatingStyles> {
        @Override public String body() { return """
            flex: none;
            display: flex;
            align-items: center;
            min-width: 0;
            user-select: none;
            touch-action: none;
            """;
        }
    }

    /**
     * The pane's icon, before its title: the holder's element — a favicon,
     * whatever it is made of — one line of the title high, never squeezed.
     * Hidden until the pane has one.
     */
    public record fp_icon() implements CssClass<FloatingStyles> {
        @Override public String body() { return """
            display: none;
            flex: none;
            align-items: center;
            justify-content: center;
            inline-size: 1.25em;
            block-size: 1.25em;
            line-height: 1;
            overflow: hidden;
            pointer-events: none;
            """;
        }
    }

    /** The icon, when the pane has one. */
    public record fp_icon_on() implements CssClass<FloatingStyles> {
        @Override public String body() { return "display: inline-flex;"; }
    }

    /** The title, one line, cut with an ellipsis. */
    public record fp_title() implements CssClass<FloatingStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            """;
        }
    }

    /** The body: what the pane holds, filling what the head leaves, scrolling beyond it; and the positioning context for it, so a widget that fills its host by absolute inset fills THE BODY and never climbs over the head. */
    public record fp_body() implements CssClass<FloatingStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-height: 0;
            display: flex;
            flex-direction: column;
            overflow: auto;
            position: relative;
            """;
        }
    }

    /** The grip: the corner that sizes the pane; draws nothing, catches the hand. */
    public record fp_grip() implements CssClass<FloatingStyles> {
        @Override public String body() { return """
            position: absolute;
            right: 0;
            bottom: 0;
            width: 16px;
            height: 16px;
            cursor: nwse-resize;
            touch-action: none;
            """;
        }
    }

    @Override
    public List<CssClass<FloatingStyles>> cssClasses() {
        return List.of(new fp_desk(), new fp_desk_layer(), new fp_frame(), new fp_head(), new fp_icon(), new fp_icon_on(), new fp_title(), new fp_body(), new fp_grip());
    }
}
