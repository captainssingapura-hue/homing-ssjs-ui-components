package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;

import java.util.List;
import java.util.Set;

/**
 * The cards' classes: a card, and the summary it commonly holds - its head, its line and its foot - placed here, their looks the
 * house's.
 */
public record CardStyles() implements CssGroup<CardStyles> {

    public static final CardStyles INSTANCE = new CardStyles();

    /**
     * A card, plain or clickable: what its caller puts in it fits it, a column
     * of what it holds, and scrolls beyond it; a host may cap it, never stretch
     * it. Its look - a raised box whose measure is its own, square at aspect 0;
     * the clickable one lifted under the hand - is the house's.
     */
    public record el_card() implements CssClass<CardStyles> {
        @Override public String body() { return """
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            flex: none;
            max-inline-size: 100%;
            min-height: 0;
            overflow: auto;
            """;
        }
    }

    /**
     * The summary: its head, its line of summary and its foot in a column, the
     * room between them its own and grown by its size, the same in every
     * style; it takes the room what holds it gives, the summary between taking
     * what the head and the foot leave.
     */
    public record el_summary() implements CssClass<CardStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--size")); }   // its size, set by the summary
        @Override public String body() { return """
            display: flex;
            flex-direction: column;
            gap: calc(8px * pow(1.3, var(--size, 0)));
            flex: 1 1 auto;
            min-width: 0;
            min-height: 0;
            """;
        }
    }

    /** The head: the title and, at its end, the tag; fixed. */
    public record el_summary_head() implements CssClass<CardStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--size")); }   // its size, set by the summary
        @Override public String body() { return """
            flex: none;
            display: flex;
            align-items: baseline;
            gap: calc(8px * pow(1.3, var(--size, 0)));
            min-width: 0;
            """;
        }
    }

    /** The title: on one line, cut short where the head ends. */
    public record el_summary_title() implements CssClass<CardStyles> {
        @Override public String body() { return """
            margin: 0;
            min-width: 0;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            """;
        }
    }

    /** The tag, at the head's end. */
    public record el_summary_tag() implements CssClass<CardStyles> {
        @Override public String body() { return """
            margin-left: auto;
            flex: none;
            """;
        }
        /** A text's own rule clears its margins, so this follows that rule. */
        @Override public List<CssClass<?>> dependsOn() { return List.of(new ElementStyles.el_text()); }
    }

    /** The line of summary: what the head and the foot leave, scrolling beyond it. */
    public record el_summary_text() implements CssClass<CardStyles> {
        @Override public String body() { return """
            margin: 0;
            flex: 1 1 auto;
            min-height: 0;
            overflow: auto;
            """;
        }
    }

    /** The foot: the way to the thing; fixed at the bottom. */
    public record el_summary_foot() implements CssClass<CardStyles> {
        @Override public String body() { return """
            flex: none;
            display: flex;
            align-items: baseline;
            """;
        }
    }

    @Override
    public List<CssClass<CardStyles>> cssClasses() {
        return List.of(new el_card(), new el_summary(), new el_summary_head(), new el_summary_title(), new el_summary_tag(), new el_summary_text(),
                       new el_summary_foot());
    }
}
