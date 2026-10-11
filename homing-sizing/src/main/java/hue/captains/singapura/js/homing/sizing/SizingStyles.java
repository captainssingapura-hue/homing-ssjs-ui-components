package hue.captains.singapura.js.homing.sizing;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * The handle's classes: its behaviour, never a look - the line it is armed on is its layout's part,
 * in its layout's look. What is here is what any sizable boundary does alike: a resize cursor while
 * it is sizable, the hand's reach a few pixels past the line either way, so a line of one pixel is
 * seven to take, and the press kept from scrolling and from selecting. Provided: none of it varies.
 */
public record SizingStyles() implements CssGroup<SizingStyles> {

    public static final SizingStyles INSTANCE = new SizingStyles();

    /** Taken by the hand: above its neighbours so its reach is, kept from scrolling and selecting; not sizable now, not taken at all. */
    public record sz_handle() implements CssClass<SizingStyles> {
        @Override public String body() { return """
            position: relative;
            z-index: 1;
            touch-action: none;
            user-select: none;
            &[aria-disabled="true"] { cursor: auto; pointer-events: none; }
            """;
        }
    }

    /** On a rail along x: the line stands up, moves sideways, and is reached from either side. */
    public record sz_handle_h() implements CssClass<SizingStyles> {
        @Override public String body() { return """
            cursor: col-resize;
            &::before { content: ""; position: absolute; inset: 0 -3px; }
            """;
        }
    }

    /** On a rail along y: the line lies down, moves up and down, and is reached from above and below. */
    public record sz_handle_v() implements CssClass<SizingStyles> {
        @Override public String body() { return """
            cursor: row-resize;
            &::before { content: ""; position: absolute; inset: -3px 0; }
            """;
        }
    }

    @Override
    public List<CssClass<SizingStyles>> cssClasses() {
        return List.of(new sz_handle(), new sz_handle_h(), new sz_handle_v());
    }
}
