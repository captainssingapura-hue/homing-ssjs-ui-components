package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;

import java.util.List;
import java.util.Set;


/**
 * The elements' classes. Every colour, face, edge, corner and motion is a
 * design word worn; the bodies hold layout and nothing that could be a
 * value. A button is a control that is primary or plain and shows a focus
 * ring, and wears one colour word — primary, secondary, danger, warning,
 * success, or plain — complete: the surface, the ink on it, the edge, all
 * scaled together by the element's extent; a card is a raised box with a
 * heading, a line and a link.
 */
public record ElementStyles() implements CssGroup<ElementStyles> {

    public static final ElementStyles INSTANCE = new ElementStyles();

    // ── Button ────────────────────────────────────────────────────────────────

    /**
     * What every button is: the base button — {@code Control.Button.Base}, the
     * control that completes a task — interactive, labelled. Its rule carries
     * the ring on focus and leaves the edge to the colour word; its density
     * (inset, gap, least width) and its label's type are the design's, and
     * grow with the element's size.
     */
    public record el_button() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            font: inherit;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            """;
        }
    }

    /**
     * The primary button: the accent surface, the ink for it, its edge — a
     * semantic surface complete, every visual surface of it scaled together by
     * the element's extent: at 1 the word as bound, at 0 the design's neutral,
     * at −1 the meaning turned the other way. Each design anchors each.
     */
    public record el_button_primary() implements CssClass<ElementStyles> {
        @Override public String body() { return ""; }
    }

    /** The secondary button: the secondary surface, the ink for it, its edge; scaled as the primary is. */
    public record el_button_secondary() implements CssClass<ElementStyles> {
        @Override public String body() { return ""; }
    }

    /** The danger button; at −1 the design's safety, whatever that looks like there. */
    public record el_button_danger() implements CssClass<ElementStyles> {
        @Override public String body() { return ""; }
    }

    /** The warning button; at −1 the design's calm. */
    public record el_button_warning() implements CssClass<ElementStyles> {
        @Override public String body() { return ""; }
    }

    /** The success button; at −1 the design's failure. */
    public record el_button_success() implements CssClass<ElementStyles> {
        @Override public String body() { return ""; }
    }

    /** The plain button: raised, in body ink. Not a semantic surface; it does not scale. */
    public record el_button_plain() implements CssClass<ElementStyles> {
        @Override public String body() { return ""; }
    }

    /**
     * A button that is on: what the design does to an interactive thing on
     * hover and on press — a lift and a shadow, a shift, a glow, a wobble —
     * worn only while on, so an off button neither lifts nor presses.
     */
    public record el_button_on() implements CssClass<ElementStyles> {
        @Override public String body() { return ""; }
    }

    /** A button that is off: inert, and says so. Toggled beside {@code disabled}, in place of {@link el_button_on}. */
    public record el_button_off() implements CssClass<ElementStyles> {
        @Override public String body() { return ""; }
    }

    // ── Icon button ───────────────────────────────────────────────────────────

    /**
     * An icon button's face: the icon it holds, centred in its box. The icon is a
     * grid item there, so its own drop for sitting in a line of text - its
     * vertical-align - does not apply. Layout only: the button's look is its casting's.
     */
    public record el_icon_button() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: inline-grid;
            place-items: center;
            """;
        }
    }

    // ── Text ──────────────────────────────────────────────────────────────────

    /** A text leaf's element: no margin of its own - where it sits, and the room round it, are its owner's. Layout only. */
    public record el_text() implements CssClass<ElementStyles> {
        @Override public String body() { return "margin: 0;\n"; }
    }

    // ── Slider ────────────────────────────────────────────────────────────────

    /** The slider: a row of label, rail and readout — {@code Control.Slider} for its air and its gap. */
    public record el_slider() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            box-sizing: border-box;
            """;
        }
    }

    /** The label before the rail; a caller stacking sliders gives them one label width, {@code --sl-label}, so the rails align. */
    public record el_slider_label() implements CssClass<ElementStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sl-label")); }
        @Override public String body() { return """
            flex: none;
            inline-size: var(--sl-label, auto);
            """;
        }
    }

    /**
     * The rail: the box the hand works in — the control's measure inline,
     * the least height its knob needs — holding the track, the detent and
     * the knob at their places; the fractions of the length are set on it
     * — {@code --sl-value}, {@code --sl-rest}, {@code --sl-from},
     * {@code --sl-span} — and read by the parts that draw them.
     */
    public record el_slider_rail() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            position: relative;
            flex: none;
            touch-action: none;
            """;
        }
    }

    /** The track: the groove, sunk, across the rail at its middle. */
    public record el_slider_track() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            position: absolute;
            inset-inline: 0;
            top: 50%;
            translate: 0 -50%;
            box-sizing: border-box;
            overflow: hidden;
            pointer-events: none;
            """;
        }
    }

    /** The fill: the track's inside from the detent to the value, in the primary surface. */
    public record el_slider_fill() implements CssClass<ElementStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sl-from"), new CssVar("--sl-span")); }
        @Override public String body() { return """
            position: absolute;
            top: 0;
            bottom: 0;
            left: var(--sl-from, 0%);
            width: var(--sl-span, 0%);
            """;
        }
    }

    /** The detent: the notch at the rest, drawn in muted ink at the design's width and height. */
    public record el_slider_detent() implements CssClass<ElementStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sl-rest")); }
        @Override public String body() { return """
            position: absolute;
            top: 50%;
            left: var(--sl-rest, 0%);
            translate: -50% -50%;
            pointer-events: none;
            """;
        }
    }

    /**
     * The knob: what the hand takes and the keys go to — role slider, the
     * focusable part — placed at the value across the rail. Its face, its
     * extent, its ring and its hand are the slider knob's own look.
     */
    public record el_slider_knob() implements CssClass<ElementStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sl-value")); }
        @Override public String body() { return """
            position: absolute;
            top: 50%;
            left: var(--sl-value, 0%);
            translate: -50% -50%;
            box-sizing: border-box;
            """;
        }
    }

    /** The mark on the knob: an {@code Icon}, centred over the face, at the size the design gives a knob's mark — the knob's own, when it is the knob. */
    public record el_slider_mark() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            position: absolute;
            top: 50%;
            left: 50%;
            translate: -50% -50%;
            """;
        }
    }

    /** The readout after the rail: the value as the caller formats it, in a steady face, right-aligned. */
    public record el_slider_readout() implements CssClass<ElementStyles> {
        // its steady width a flex basis, not a min-width: how small a text may be is its look's
        @Override public String body() { return """
            flex: 0 0 4ch;
            text-align: end;
            white-space: nowrap;
            """;
        }
    }

    // ── Slider, vertical: a fader ─────────────────────────────────────────────
    //
    // The same words, turned: the rail takes a vertical writing mode, so the
    // design's extent — its length inline, its room across — stands up with
    // it, and the parts are placed from the bottom by the same fractions. A
    // vertical slider's knob is the cap, wide across the track and low along
    // it; the mark on it is set upright again.

    /** The slider stood up: label above, rail, readout below. */
    public record el_slider_vertical() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex-direction: column;
            align-items: center;
            """;
        }
    }

    /** The rail stood up: the design's inline length runs downward, its block room across. */
    public record el_slider_rail_vertical() implements CssClass<ElementStyles> {
        @Override public String body() { return "writing-mode: vertical-lr;"; }
    }

    /** A rail with a scale beside it leaves the room the ticks' captions take, after it in the block direction: below a flat one, beside a standing one. */
    public record el_slider_rail_ticked() implements CssClass<ElementStyles> {
        @Override public String body() { return "margin-block-end: 2.2em;"; }
    }

    /** The track stood up: across the rail's middle, top to bottom. */
    public record el_slider_track_vertical() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            inset-inline: auto;
            top: 0;
            bottom: 0;
            left: 50%;
            translate: -50% 0;
            """;
        }
    }

    /** The fill stood up: from the detent's height to the value's. */
    public record el_slider_fill_vertical() implements CssClass<ElementStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sl-from"), new CssVar("--sl-span")); }
        @Override public String body() { return """
            left: 0;
            right: 0;
            top: auto;
            width: auto;
            bottom: var(--sl-from, 0%);
            height: var(--sl-span, 0%);
            """;
        }
    }

    /** The detent stood up: at its height, across the track. */
    public record el_slider_detent_vertical() implements CssClass<ElementStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sl-rest")); }
        @Override public String body() { return """
            top: auto;
            left: 50%;
            bottom: var(--sl-rest, 0%);
            translate: -50% 50%;
            """;
        }
    }

    /**
     * The cap: a vertical slider's knob — the box the hand takes and the
     * keys go to, at the cap's extent (low along the track, wide across it),
     * ringed on focus by the cap's own rule, placed at the value's height.
     */
    public record el_slider_cap() implements CssClass<ElementStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sl-value")); }
        @Override public String body() { return """
            position: absolute;
            left: 50%;
            bottom: var(--sl-value, 0%);
            translate: -50% 50%;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            justify-content: center;
            """;
        }
    }

    /** The mark on the cap, upright again, at the size the design gives a cap's mark. */
    public record el_slider_cap_mark() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            position: relative;
            writing-mode: horizontal-tb;
            """;
        }
    }

    /**
     * A tick of a scale: a line at a value, its caption beside it; placed
     * after the rail in the block direction — below a flat slider, in a
     * column; beside a standing one, in a row. Upright whatever the rail.
     */
    public record el_slider_tick() implements CssClass<ElementStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sl-tick")); }
        @Override public String body() { return """
            position: absolute;
            left: var(--sl-tick, 0%);
            top: 100%;
            translate: -50% 0;
            margin-top: 2px;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 2px;
            writing-mode: horizontal-tb;
            pointer-events: none;
            """;
        }
    }

    /** A tick beside a standing rail: at its height, to the right, a row. */
    public record el_slider_tick_vertical() implements CssClass<ElementStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sl-tick")); }
        @Override public String body() { return """
            left: 100%;
            top: auto;
            bottom: var(--sl-tick, 0%);
            translate: 0 50%;
            margin-top: 0;
            margin-left: 3px;
            flex-direction: row;
            gap: 4px;
            """;
        }
    }

    /** The tick's line: the design's tick, along and across the track, in muted ink. */
    public record el_slider_tick_line() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex: none;
            """;
        }
    }

    /** The tick's line beside a standing rail: turned with it, so along is up and across is level. */
    public record el_slider_tick_line_vertical() implements CssClass<ElementStyles> {
        @Override public String body() { return "writing-mode: vertical-lr;"; }
    }

    /** The tick's caption: its number, small and muted, in a steady face. */
    public record el_slider_tick_label() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            white-space: nowrap;
            """;
        }
    }

    /** A slider that is off: inert, and says so. */
    public record el_slider_off() implements CssClass<ElementStyles> {
        @Override public String body() { return "pointer-events: none;"; }
    }

    // ── Slider group ──────────────────────────────────────────────────────────

    /**
     * The group: sliders that share the keys, one holder in the party for
     * all of them and one of them current. A column, the header then the
     * body; its look - framed as a card is, ringed while it holds the keys -
     * is the house's.
     */
    public record el_slider_group() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            min-width: 0;
            """;
        }
    }

    /** The header: the title; a press on it makes the group the holder of the keys. */
    public record el_slider_group_header() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex: none;
            display: flex;
            align-items: baseline;
            min-width: 0;
            """;
        }
    }

    /** The title in the header: a heading. */
    public record el_slider_group_title() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            margin: 0;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            """;
        }
    }

    /** The body: the sliders, stacked. */
    public record el_slider_group_body() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: flex;
            flex-direction: column;
            min-width: 0;
            """;
        }
    }

    /** The body laid across: the sliders side by side, as faders on a mixer, each with the room its scale takes. */
    public record el_slider_group_body_across() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex-direction: row;
            align-items: flex-start;
            flex-wrap: wrap;
            """;
        }
    }

    // ── Card ──────────────────────────────────────────────────────────────────

    /**
     * A card, plain or clickable: what its caller puts in it fits it, a column
     * of what it holds, and scrolls beyond it; a host may cap it, never stretch
     * it. Its look - a raised box whose measure is its own, square at aspect 0;
     * the clickable one lifted under the hand - is the house's.
     */
    public record el_card() implements CssClass<ElementStyles> {
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

    // ── Items ─────────────────────────────────────────────────────────────────

    /** A menu's item: its glyph, its name, its hint and its way on in a row, on one line. */
    public record el_menu_item() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            white-space: nowrap;
            """;
        }
    }

    /** Its name: the room its glyph and its hint leave. */
    public record el_menu_item_label() implements CssClass<ElementStyles> {
        @Override public String body() { return "flex: 1 1 auto;"; }
    }

    public record el_menu_item_hint() implements CssClass<ElementStyles> {
        @Override public String body() { return "flex: none;"; }
    }

    /** Its way into its rows, at its end. */
    public record el_menu_item_disclose() implements CssClass<ElementStyles> {
        @Override public String body() { return "margin-inline-start: auto;"; }
    }

    /** A choice's option: its name and, at its end, its note, on a line; its words from its start. */
    public record el_choice_option() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: baseline;
            text-align: left;
            box-sizing: border-box;
            """;
        }
    }

    /** Its note, at its end, cut short where the option ends. */
    public record el_choice_option_note() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            margin-left: auto;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            """;
        }
    }

    // ── Summary ───────────────────────────────────────────────────────────────

    /**
     * The summary: its head, its line of summary and its foot in a column, the
     * room between them its own and grown by its size, the same in every
     * style; it takes the room what holds it gives, the summary between taking
     * what the head and the foot leave.
     */
    public record el_summary() implements CssClass<ElementStyles> {
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
    public record el_summary_head() implements CssClass<ElementStyles> {
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
    public record el_summary_title() implements CssClass<ElementStyles> {
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
    public record el_summary_tag() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            margin-left: auto;
            flex: none;
            """;
        }
    }

    /** The line of summary: what the head and the foot leave, scrolling beyond it. */
    public record el_summary_text() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            margin: 0;
            flex: 1 1 auto;
            min-height: 0;
            overflow: auto;
            """;
        }
    }

    /** The foot: the way to the thing; fixed at the bottom. */
    public record el_summary_foot() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex: none;
            display: flex;
            align-items: baseline;
            """;
        }
    }

    // ── The panel: a head, a body, and what is mounted in it ──────────────

    /**
     * The panel: a column of head over body that fills what holds it — a cell,
     * a pane, a page. Its corner, its rule and its edge are the container's,
     * which is also where the keys mark comes from, so a panel that holds the
     * keys says so without a word of its own; its gap is the panel's, and a
     * design that draws no line under the head parts them with it.
     */
    public record el_panel() implements CssClass<ElementStyles> {
        // The panel's depth is the design's to draw and the holder's to set: the shadow is worn plainly here, and
        // the design answers it at ELEVATED and SUNKEN — in whatever plane it honestly uses for depth, or not at
        // all. Nothing here says what a register MEANS; the app says that, in its own code.
        @Override public String body() { return """
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            flex: 1 1 auto;
            align-self: stretch;
            min-width: 0;
            min-height: 0;
            overflow: hidden;
            """;
        }
    }

    /**
     * The head: the bar that names the panel and carries what acts on it — its
     * air, its gap, and the line under it, which is the design's own word about
     * panels; the line's colour is the divider's, as a strip's is.
     */
    public record el_panel_head() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex: none;
            display: flex;
            align-items: center;
            box-sizing: border-box;
            min-width: 0;
            user-select: none;
            """;
        }
    }

    /** The panel's name: a kicker, as a labelled region's is; it ellipsises rather than push out what acts on the panel. */
    public record el_panel_title() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            margin: 0;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            """;
        }
    }

    /** The slot at the end of the head: what acts on the panel, pushed to the end and never squeezed. */
    public record el_panel_slot() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            margin-inline-start: auto;
            flex: none;
            display: flex;
            align-items: center;
            """;
        }
    }

    /**
     * The body: what the head leaves, flush — for a panel that is filled by
     * what is mounted in it, a dock or a grid, which scrolls on its own. It is
     * the positioning context for what it holds, so a widget that fills its
     * host by absolute inset fills THE BODY and never climbs over the head.
     */
    public record el_panel_body() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            min-width: 0;
            min-height: 0;
            overflow: hidden;
            position: relative;
            """;
        }
    }

    /** The body of a panel that holds content rather than a widget: the design's air around it, its gap between the things in it, and the scroll. */
    public record el_panel_body_air() implements CssClass<ElementStyles> {
        @Override public String body() { return "overflow: auto;"; }
    }

    // ── The browser's own controls ────────────────────────────────────────────

    /**
     * A checkbox the browser draws, its mark in the colour its look gives it (the accent): an owner realizing the house's
     * checkbox on a native one wears this beside its look, as a range does. No margin of its own: where it sits is its owner's.
     */
    public record el_checkbox() implements CssClass<ElementStyles> {
        @Override public String body() { return "accent-color: currentColor;\nmargin: 0;\n"; }
    }

    // ── The pane's thumbnail ──────────────────────────────────────────────────

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

    // ── The switch ────────────────────────────────────────────────────────────

    /** The switch: its groove and its words in a row, the room between them its look. */
    public record el_switch() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: inline-flex;
            align-items: center;
            """;
        }
    }

    /** The switch's groove: its own size, the knob placed in it. */
    public record el_switch_groove() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: inline-block;
            vertical-align: middle;
            box-sizing: content-box;
            width: 36px;
            height: 20px;
            position: relative;
            flex: none;
            """;
        }
    }

    /** The switch's knob: at the start of its groove, at its end while the switch is on. */
    public record el_switch_knob() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            position: absolute;
            box-sizing: border-box;
            top: 2px;
            left: 2px;
            width: 16px;
            height: 16px;
            [aria-checked="true"] & { translate: 16px 0; }
            """;
        }
    }

    // ── Edge strip ────────────────────────────────────────────────────────────

    /**
     * The strip: a bar of controls lying over the foot of its host, above what
     * the host holds. It has a panel head's air and gap — a bar that holds what
     * acts on a room — the raised face, and the overlay's shadow, since it lies
     * on the room rather than in it. Its controls keep their labels on one
     * line, and wrap whole to a second row when the host is narrow.
     */
    public record el_strip() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            position: absolute;
            inset-inline: 0;
            bottom: 0;
            z-index: 2;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            box-sizing: border-box;
            min-width: 0;
            white-space: nowrap;
            """;
        }
    }

    /** The strip while it is not asked for: not there to the eye or the hand. */
    public record el_strip_hidden() implements CssClass<ElementStyles> {
        @Override public String body() { return "visibility: hidden;"; }
    }

    /** The lip: the thin band at the host's foot the hand comes to, in the strip's own face — the strip's edge, showing. */
    public record el_strip_lip() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex: none;
            block-size: 6px;
            """;
        }
    }

    // ── a site's page: its bar, the brand and the trail it carries - placed here, their looks the house's ──

    /** The site's bar: what it carries in a row, centred across it; its room, its ground and its line are its look. */
    public record el_site_bar() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            box-sizing: border-box;
            """;
        }
    }

    /** What the bar carries at its start - the brand: never shrinking. */
    public record el_site_bar_start() implements CssClass<ElementStyles> {
        @Override public String body() { return "flex: 0 0 auto;"; }
    }

    /** What the bar carries across its middle - the trail: the room the others leave, and no less than nothing. */
    public record el_site_bar_way() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-width: 0;
            """;
        }
    }

    /** What the bar carries at its end - the preferences: pushed to it, never shrinking. */
    public record el_site_bar_end() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex: 0 0 auto;
            margin-inline-start: auto;
            """;
        }
    }

    /** The brand: its mark beside its name, the room between them its look. */
    public record el_brand() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            """;
        }
    }

    /** The brand's mark: a square, until a site gives a logo; its colour is the brand's. */
    public record el_brand_mark() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            width: 12px;
            height: 12px;
            flex: 0 0 auto;
            """;
        }
    }

    /** The trail: its steps in a row, the room between them its look. */
    public record el_trail() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            min-width: 0;
            """;
        }
    }

    /** A step of the trail: on one line. */
    public record el_trail_crumb() implements CssClass<ElementStyles> {
        @Override public String body() { return "white-space: nowrap;"; }
    }

    @Override
    public List<CssClass<ElementStyles>> cssClasses() {
        return List.of(new el_button(), new el_button_primary(), new el_button_secondary(), new el_button_danger(), new el_button_warning(), new el_button_success(),
                       new el_button_plain(), new el_button_on(), new el_button_off(), new el_icon_button(), new el_text(),
                       new el_slider(), new el_slider_label(), new el_slider_rail(), new el_slider_track(), new el_slider_fill(), new el_slider_detent(),
                       new el_slider_knob(), new el_slider_mark(), new el_slider_readout(),
                       new el_slider_vertical(), new el_slider_rail_vertical(), new el_slider_rail_ticked(), new el_slider_track_vertical(), new el_slider_fill_vertical(), new el_slider_detent_vertical(),
                       new el_slider_cap(), new el_slider_cap_mark(), new el_slider_tick(), new el_slider_tick_vertical(), new el_slider_tick_line(), new el_slider_tick_line_vertical(), new el_slider_tick_label(),
                       new el_slider_off(),
                       new el_slider_group(), new el_slider_group_header(), new el_slider_group_title(), new el_slider_group_body(), new el_slider_group_body_across(),
                       new el_menu_item(), new el_menu_item_label(), new el_menu_item_hint(), new el_menu_item_disclose(), new el_choice_option(), new el_choice_option_note(),
                       new el_card(), new el_summary(), new el_summary_head(), new el_summary_title(), new el_summary_tag(), new el_summary_text(), new el_summary_foot(),
                       new el_panel(), new el_panel_head(), new el_panel_title(), new el_panel_slot(), new el_panel_body(), new el_panel_body_air(),
                       new el_checkbox(), new el_pane_thumb_name(), new el_switch(), new el_switch_groove(), new el_switch_knob(),
                       new el_strip(), new el_strip_hidden(), new el_strip_lip(),
                       new el_site_bar(), new el_site_bar_start(), new el_site_bar_way(), new el_site_bar_end(),
                       new el_brand(), new el_brand_mark(), new el_trail(), new el_trail_crumb());
    }
}
