package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;

import java.util.List;
import java.util.Set;

/**
 * The floating pane's classes, its frame, its head and its eight sizing edges: moved from the floating jar with it (23 Oct). What
 * it looks like is a pane's look; the bodies hold layout and nothing that could be a value.
 */
public record FloatingPaneStyles() implements CssGroup<FloatingPaneStyles> {

    public static final FloatingPaneStyles INSTANCE = new FloatingPaneStyles();

    /**
     * The frame: a raised box above the desk with the overlay's shadow, at the
     * place and measure its user gave it — {@code --fp-x}, {@code --fp-y},
     * {@code --fp-w}, {@code --fp-h} — and on the stack at {@code --fp-z}.
     * Its rule carries the ring on focus; its corner and rule are a container's.
     */
    public record fp_frame() implements CssClass<FloatingPaneStyles> {
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
    public record fp_head() implements CssClass<FloatingPaneStyles> {
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
    public record fp_icon() implements CssClass<FloatingPaneStyles> {
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
    public record fp_icon_on() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return "display: inline-flex;"; }
    }

    /** The title, one line, cut with an ellipsis. */
    public record fp_title() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            """;
        }
    }

    /** The body: what the pane holds, filling what the head leaves, scrolling beyond it; and the positioning context for it, so a widget that fills its host by absolute inset fills THE BODY and never climbs over the head. */
    public record fp_body() implements CssClass<FloatingPaneStyles> {
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

    /** A handle that sizes the pane, by the hand (23 Oct): unseen, over its edge, catching the hand - its cursor alone saying so. */
    public record fp_size() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return """
            position: absolute;
            z-index: 3;
            touch-action: none;
            """;
        }
    }

    /** The handle along its top edge. */
    public record fp_size_n() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return """
            top: 0;
            inset-inline: 10px;
            block-size: 5px;
            cursor: ns-resize;
            """;
        }
    }

    /** The handle along its foot edge. */
    public record fp_size_s() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return """
            bottom: 0;
            inset-inline: 10px;
            block-size: 5px;
            cursor: ns-resize;
            """;
        }
    }

    /** The handle along its right edge. */
    public record fp_size_e() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return """
            right: 0;
            inset-block: 10px;
            inline-size: 5px;
            cursor: ew-resize;
            """;
        }
    }

    /** The handle along its left edge. */
    public record fp_size_w() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return """
            left: 0;
            inset-block: 10px;
            inline-size: 5px;
            cursor: ew-resize;
            """;
        }
    }

    /** The handle along its top right corner. */
    public record fp_size_ne() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return """
            top: 0;
            right: 0;
            inline-size: 10px;
            block-size: 10px;
            cursor: nesw-resize;
            """;
        }
    }

    /** The handle along its top left corner. */
    public record fp_size_nw() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return """
            top: 0;
            left: 0;
            inline-size: 10px;
            block-size: 10px;
            cursor: nwse-resize;
            """;
        }
    }

    /** The handle along its foot right corner. */
    public record fp_size_se() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return """
            bottom: 0;
            right: 0;
            inline-size: 10px;
            block-size: 10px;
            cursor: nwse-resize;
            """;
        }
    }

    /** The handle along its foot left corner. */
    public record fp_size_sw() implements CssClass<FloatingPaneStyles> {
        @Override public String body() { return """
            bottom: 0;
            left: 0;
            inline-size: 10px;
            block-size: 10px;
            cursor: nesw-resize;
            """;
        }
    }

    @Override
    public List<CssClass<FloatingPaneStyles>> cssClasses() {
        return List.of(new fp_frame(), new fp_head(), new fp_icon(), new fp_icon_on(), new fp_title(), new fp_body(), new fp_size(), new fp_size_n(),
                       new fp_size_s(), new fp_size_e(), new fp_size_w(), new fp_size_ne(), new fp_size_nw(), new fp_size_se(), new fp_size_sw());
    }
}
