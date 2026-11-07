package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * The status footer's and the expandable footer area's classes (23 Oct): placed here, their looks the house's.
 */
public record StatusFooterStyles() implements CssGroup<StatusFooterStyles> {

    public static final StatusFooterStyles INSTANCE = new StatusFooterStyles();

    /** The status footer: what it shows on one row, its cover over it. */
    public record el_status_footer() implements CssClass<StatusFooterStyles> {
        @Override public String body() { return """
            position: relative;
            display: flex;
            align-items: center;
            box-sizing: border-box;
            min-width: 0;
            white-space: nowrap;
            """;
        }
    }

    /** Where its status widget shows: the row, all of it. */
    public record el_status_room() implements CssClass<StatusFooterStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-width: 0;
            overflow: hidden;
            """;
        }
    }

    /** Over what it shows, unseen: the hand lands on it, never on what is beneath - read-only, still read aloud. */
    public record el_status_cover() implements CssClass<StatusFooterStyles> {
        @Override public String body() { return """
            position: absolute;
            inset: 0;
            """;
        }
    }

    /** The expandable footer area: its status footer at its foot, its area standing on it. */
    public record el_footer_area() implements CssClass<StatusFooterStyles> {
        @Override public String body() { return """
            position: relative;
            flex: none;
            """;
        }
    }

    /** Its area: over what is above it, as wide as it, as tall as its widget asks, up to most of the page. */
    public record el_footer_area_panel() implements CssClass<StatusFooterStyles> {
        @Override public String body() { return """
            position: absolute;
            inset-inline: 0;
            bottom: 100%;
            z-index: 2;
            max-height: 60vh;
            overflow: auto;
            box-sizing: border-box;
            """;
        }
    }

    /** Its area, while closed: not there to the eye or the hand. */
    public record el_footer_area_closed() implements CssClass<StatusFooterStyles> {
        @Override public String body() { return """
            display: none;
            """;
        }
    }

    @Override
    public List<CssClass<StatusFooterStyles>> cssClasses() {
        return List.of(new el_status_footer(), new el_status_room(), new el_status_cover(), new el_footer_area(), new el_footer_area_panel(),
                       new el_footer_area_closed());
    }
}
