package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;

import java.util.List;
import java.util.Set;


/**
 * The panes' classes. A pane is a base surface. Its strip, its chips and the
 * mark where a dragged chip would land wear the house's look - a tab strip's,
 * a tab's, a drop mark's - so their classes here only place them: the bodies
 * hold layout and nothing that could be a value.
 *
 * <p>Names are {@code mtp_}; the studio's pane paints {@code .hmtp-} from an
 * injected sheet, so both may be on one page while the swap is under way.</p>
 */
public record PaneStyles() implements CssGroup<PaneStyles> {

    public static final PaneStyles INSTANCE = new PaneStyles();

    /** The pane: a column of strip over content, filling its host by growing — so the host is a flex column and the pane its item. */
    public record mtp_pane() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            display: flex;
            flex-direction: column;
            flex: 1 1 auto;
            align-self: stretch;
            min-height: 0;
            position: relative;
            """;
        }
    }

    /**
     * The strip: a row, scrolling sideways when the chips overflow. The chips sit on its bottom edge; the room
     * above them for a lift is the rail's, since the rail is what clips. Its look - raised, ruled along its foot,
     * one tab tall and that room whether or not it holds a tab, lit while current - is a tab strip's.
     */
    public record mtp_strip() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: flex-end;
            flex-shrink: 0;
            overflow: hidden;
            """;
        }
    }

    /**
     * A chip: its look is a tab's - its measure, its frame, its colours and how it moves, by state - and here
     * only its row: what it holds in a line, squeezed no wider than the row allows, the label ellipsised within.
     * Dragged by the pointer, so no touch scrolling on it.
     */
    public record mtp_chip() implements CssClass<PaneStyles> {
        /** The squeeze, set on the rail and read here. */
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--chip-fit")); }
        @Override public String body() { return """
            display: inline-flex;
            align-items: center;
            box-sizing: border-box;
            overflow: hidden;
            white-space: nowrap;
            flex: none;
            max-inline-size: var(--chip-fit, none);
            touch-action: none;
            position: relative;
            """;
        }
    }

    /**
     * The mark on a chip while the keys are inside its tab: the within icon,
     * its picture its look, the style worn's (D52) - an icon that says where
     * the keys are - placed here, never in the way of the hand. It is there only while the keys are in the tab — the chip says
     * *held* while the bar has them, and *lent* with this mark while what the
     * tab holds has them.
     */
    public record mtp_chip_mark() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            display: none;
            flex: none;
            align-items: center;
            justify-content: center;
            inline-size: 1.1em;
            block-size: 1em;
            line-height: 1;
            font-style: normal;
            pointer-events: none;
            user-select: none;
            """;
        }
    }

    /** The mark, while the keys are in the tab. */
    public record mtp_chip_mark_on() implements CssClass<PaneStyles> {
        @Override public String body() { return "display: inline-flex;"; }
    }

    /**
     * A tab's icon, before its label: the holder's own element — a favicon,
     * whatever it is made of — in a box one line of the chip's type high,
     * never squeezed and never in the way of the hand. Hidden until a tab has
     * one, as the mark is.
     */
    public record mtp_chip_icon() implements CssClass<PaneStyles> {
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
            user-select: none;
            """;
        }
    }

    /** The icon, when the tab has one. */
    public record mtp_chip_icon_on() implements CssClass<PaneStyles> {
        @Override public String body() { return "display: inline-flex;"; }
    }

    /** The label in a chip: takes the room the cross leaves, and ellipsises. */
    public record mtp_chip_label() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-width: 0;
            overflow: hidden;
            text-overflow: ellipsis;
            """;
        }
    }

    /**
     * The chip in the hand: over the row and under the hand along it, where
     * {@code --mtp-drag-x} put it - by its offset, which nothing eases, so it is
     * where the hand is; the tab's look draws it dragging (data-dragging),
     * lifted, with its shadow and cursor.
     */
    public record mtp_chip_dragging() implements CssClass<PaneStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--mtp-drag-x")); }
        @Override public String body() { return """
            position: relative;
            z-index: 2;
            left: var(--mtp-drag-x, 0px);
            """;
        }
    }

    /** A chip stepping aside for the one in the hand: one pitch over, {@code --mtp-shift-x}, by its translate, which the tab's look eases. */
    public record mtp_chip_shifted() implements CssClass<PaneStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--mtp-shift-x")); }
        @Override public String body() { return """
            translate: var(--mtp-shift-x, 0px) 0;
            """;
        }
    }

    /** The strip while a chip is in the hand: nothing clipped, so the lifted chip and its shadow are seen whole, over the content. */
    public record mtp_strip_loose() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            overflow: visible;
            position: relative;
            z-index: 2;
            """;
        }
    }

    /**
      * Where a chip's cross sits: the house's close button, cast as what
      * closes its owner, at the chip's end. Its look - the cross, its frame or
      * none - is the style's; this is its place, and nothing else. It takes
      * no keys, reached through the party and not by the tab order.
      */
    public record mtp_chip_close() implements CssClass<PaneStyles> {
        @Override public String body() { return "flex: none;\nalign-self: center;\n"; }
    }

    /** Where a dragged chip will land: a bar between two chips, its colour a drop mark's. */
    public record mtp_drop_mark() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            width: 3px;
            flex-shrink: 0;
            align-self: stretch;
            margin: 2px -1.5px;
            pointer-events: none;
            """;
        }
    }

    /**
      * THE WINDOW over the row. The strip is the whole bar — the rail, the
      * plus, the count — and this is the part of it the chips live in, and the
      * only part that moves. It takes what the bar can spare and no more
      * ({@code flex: 0 1 auto} over {@code min-inline-size: 0}), so with a few
      * tabs it is exactly as wide as they are and the plus sits against the
      * last of them, and with many it is the room that is left and the plus
      * has stopped following.
      *
      * <p>{@code overflow: hidden} rather than {@code auto}: the window is
      * moved by the strip, in whole tabs, and a scrollbar would offer a
      * second way of moving it that lands between two. Hidden still scrolls
      * when {@code scrollLeft} is set — that is the whole mechanism.</p>
      *
      * <p>Hidden clips both ways, so the ROOM FOR A LIFT is inside it: a chip
      * lifted off the row (4px) and moved by the design as well — up to 4px
      * pressed, 3px selected — rises within the rail's own box and keeps its
      * whole height. The strip is that room taller than a tab, to match.</p>
      */
    public record mtp_rail() implements CssClass<PaneStyles> {
        /** The width the row is squeezed to, read by every chip in it; none until there are too many. */
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--chip-fit")); }
        @Override public String body() { return """
            display: flex;
            align-items: flex-end;
            flex: 0 1 auto;
            min-inline-size: 0;
            overflow: hidden;
            padding-block-start: 8px;
            """;
        }
    }

    /** The end of the strip - the count, and a float's cross - pushed right; its room is the strip's look's. */
    public record mtp_strip_tail() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            align-self: center;
            margin-left: auto;
            flex-shrink: 0;
            """;
        }
    }

    /**
      * Where the plus sits ON THE RAIL, where a browser keeps it: after the
      * last chip, moving along as tabs come and go, because it is about THE END
      * OF THE ROW and not about the end of the bar. The plus is the house's icon
      * button, cast as what adds to its owner, holding the add icon: its look
      * is the style's; this is its place, and nothing else.
      */
    public record mtp_rail_add() implements CssClass<PaneStyles> {
        @Override public String body() { return "flex: none;\nalign-self: center;\nmargin-inline-start: 2px;\n"; }
    }

    /**
     * Where the bar's own cross sits, at its end, for a pane that is a window -
     * a float, whose one bar is its strip: pressed, the window and every tab in
     * it close. The house's close button, cast as what closes its owner: its
     * look is the style's; this is its place, and nothing else.
     */
    public record mtp_bar_close() implements CssClass<PaneStyles> {
        @Override public String body() { return "flex: none;\nalign-self: center;\n"; }
    }

    /** The count: the tabs in the pane, as a kicker. */
    public record mtp_pill() implements CssClass<PaneStyles> {
        @Override public String body() { return "white-space: nowrap;"; }
    }

    /** The content area: the tabs' panels stacked, one shown. */
    public record mtp_content() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-height: 0;
            display: flex;
            flex-direction: column;
            position: relative;
            """;
        }
    }

    /** A tab's panel: the widget's root inside, scrolling on its own. Never detached; hidden when not the active one. */
    public record mtp_tab_content() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-height: 0;
            overflow: auto;
            position: relative;
            isolation: isolate;
            """;
        }
    }

    /** A panel not shown, or the empty note while there are tabs: last in the sheet, so it wins the display. */
    public record mtp_tab_content_hidden() implements CssClass<PaneStyles> {
        @Override public String body() { return "display: none;"; }
    }



    /** What a new tab holds until it is told what to hold: the prompt over the choices, centred in the room the tab was given. */
    public record mtp_opener() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            gap: 14px;
            padding: 18px;
            min-height: 0;
            overflow: auto;
            """;
        }
    }

    /** The question, quietly: a tab that is asking is not a tab that is shouting. */
    public record mtp_opener_note() implements CssClass<PaneStyles> {
        @Override public String body() { return "margin: 0;"; }
    }

    /** The choices, as many to a row as the room allows. */
    public record mtp_opener_grid() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 10px;
            """;
        }
    }

    /** The box of an empty pane: fills the content, and gives the note it holds its room. */
    public record mtp_empty() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 24px;
            text-align: center;
            """;
        }
    }

    // ── the floating pane: moved from the floating jar with it (23 Oct) ──

    /**
     * The frame: a raised box above the desk with the overlay's shadow, at the
     * place and measure its user gave it — {@code --fp-x}, {@code --fp-y},
     * {@code --fp-w}, {@code --fp-h} — and on the stack at {@code --fp-z}.
     * Its rule carries the ring on focus; its corner and rule are a container's.
     */
    public record fp_frame() implements CssClass<PaneStyles> {
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
    public record fp_head() implements CssClass<PaneStyles> {
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
    public record fp_icon() implements CssClass<PaneStyles> {
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
    public record fp_icon_on() implements CssClass<PaneStyles> {
        @Override public String body() { return "display: inline-flex;"; }
    }

    /** The title, one line, cut with an ellipsis. */
    public record fp_title() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            """;
        }
    }

    /** The body: what the pane holds, filling what the head leaves, scrolling beyond it; and the positioning context for it, so a widget that fills its host by absolute inset fills THE BODY and never climbs over the head. */
    public record fp_body() implements CssClass<PaneStyles> {
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
    public record fp_grip() implements CssClass<PaneStyles> {
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
    public List<CssClass<PaneStyles>> cssClasses() {
        return List.of(new fp_frame(), new fp_head(), new fp_icon(), new fp_icon_on(), new fp_title(), new fp_body(), new fp_grip(),
                       new mtp_pane(), new mtp_strip(), new mtp_rail(), new mtp_strip_loose(), new mtp_chip(), new mtp_chip_icon(), new mtp_chip_icon_on(), new mtp_chip_label(), new mtp_chip_mark(), new mtp_chip_mark_on(), new mtp_chip_dragging(), new mtp_chip_shifted(),
                       new mtp_chip_close(), new mtp_drop_mark(), new mtp_strip_tail(), new mtp_rail_add(), new mtp_bar_close(),
                       new mtp_pill(), new mtp_content(), new mtp_tab_content(), new mtp_empty(),
                       new mtp_opener(), new mtp_opener_note(), new mtp_opener_grid(),
                       
                       new mtp_tab_content_hidden());   // last, so hidden wins over what a panel or the empty note says of its display
    }
}
