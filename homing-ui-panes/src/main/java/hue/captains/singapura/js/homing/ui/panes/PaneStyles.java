package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;
import hue.captains.singapura.js.homing.core.Wearable;

import java.util.List;
import java.util.Set;

import static hue.captains.singapura.js.homing.design.DesignClass.of;
import hue.captains.singapura.js.homing.design.Icon;
import static hue.captains.singapura.js.homing.design.Box.Container;
import static hue.captains.singapura.js.homing.design.Interaction.DropTarget;
import static hue.captains.singapura.js.homing.design.Layer.Base;
import static hue.captains.singapura.js.homing.design.Target.Color;
import static hue.captains.singapura.js.homing.design.Target.Type;
import static hue.captains.singapura.js.homing.design.Target.Shape;
import static hue.captains.singapura.js.homing.design.Text.Body;

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
        /** Its own word — a pane — so a design can say what a pane looks like while the keys are on it: the rule gives the mark its geometry and the edge its colour. A dock draws no frame at rest; the mark is an outline, and moves nothing. */
        @Override public List<? extends Wearable> wears() { return List.of(of(Base.class, Color.Surface.class), of(Body.class, Color.Ink.class), of(Body.class, Type.Face.class),
                                                                          of(Container.Pane.class, Shape.Rule.class), of(Container.Pane.class, Color.Edge.class)); }
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
            """;
        }
    }

    /**
     * The chip lifted: the whole operation is lift and shift, so the chip the
     * cursor is on is picked up off the row, and put down again the moment
     * the work moves into its tab or leaves the bar. It rides over its
     * neighbours while it is up.
     *
     * <p>The lift is its own translate and not the design's transform: a chip
     * that is selected already wears the tab's transform at a state, which
     * nothing plain can out-specify; the tab's look eases it.</p>
     */
    public record mtp_chip_lifted() implements CssClass<PaneStyles> {
        @Override public String body() { return """
            position: relative;
            z-index: 1;
            translate: 0 -4px;
            """;
        }
    }

    /**
     * The mark on a chip while the keys are inside its tab: the design's
     * picture for "within" - its colour the look's, an icon that says where the
     * keys are - never in the way of the hand. It is there only while the keys are in the tab — the chip says
     * *held* while the bar has them, and *lent* with this mark while what the
     * tab holds has them.
     */
    public record mtp_chip_mark() implements CssClass<PaneStyles> {
        @Override public List<? extends Wearable> wears() { return List.of(of(Icon.Within.class, Type.Glyph.class)); }
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

    /** The pane while a tab from outside is offered to it: the drop-target word, on its surface, its edge and its rule. */
    public record mtp_dock_target() implements CssClass<PaneStyles> {
        @Override public List<? extends Wearable> wears() { return List.of(of(DropTarget.class, Color.Surface.class), of(DropTarget.class, Color.Edge.class), of(DropTarget.class, Shape.Rule.class)); }
        @Override public String body() { return ""; }
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

    @Override
    public List<CssClass<PaneStyles>> cssClasses() {
        return List.of(new mtp_pane(), new mtp_strip(), new mtp_rail(), new mtp_strip_loose(), new mtp_chip(), new mtp_chip_icon(), new mtp_chip_icon_on(), new mtp_chip_label(), new mtp_chip_mark(), new mtp_chip_mark_on(), new mtp_chip_lifted(), new mtp_chip_dragging(), new mtp_chip_shifted(),
                       new mtp_chip_close(), new mtp_drop_mark(), new mtp_strip_tail(), new mtp_rail_add(), new mtp_bar_close(),
                       new mtp_pill(), new mtp_content(), new mtp_tab_content(), new mtp_empty(), new mtp_dock_target(),
                       new mtp_opener(), new mtp_opener_note(), new mtp_opener_grid(),
                       
                       new mtp_tab_content_hidden());   // last, so hidden wins over what a panel or the empty note says of its display
    }
}
