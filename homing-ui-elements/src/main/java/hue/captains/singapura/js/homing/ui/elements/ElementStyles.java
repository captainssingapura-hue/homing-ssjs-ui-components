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

    /** The slider: a row of label, range and readout — {@code Control.Slider} for its air and its gap. */
    public record el_slider() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            box-sizing: border-box;
            """;
        }
    }

    /** The label before the range: as wide as its words. */
    public record el_slider_label() implements CssClass<ElementStyles> {
        @Override public String body() { return "flex: none;\n"; }
    }

    /** The range between them: the rest of the row (23 Oct). */
    public record el_slider_range() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-inline-size: 0;
            """;
        }
    }







    /** The readout after the range: the value as the caller formats it, in a steady face, right-aligned. */
    public record el_slider_readout() implements CssClass<ElementStyles> {
        // its steady width a flex basis, not a min-width: how small a text may be is its look's
        @Override public String body() { return """
            flex: 0 0 4ch;
            text-align: end;
            white-space: nowrap;
            """;
        }
    }
















    // ── Slider group ──────────────────────────────────────────────────────────

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

    /** A menu item's leading room: an icon's box, kept whether an icon is mounted or not, so the names beside it align. */
    public record el_menu_item_lead() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: inline-flex;
            align-items: center;
            justify-content: center;
            flex: none;
            inline-size: 1.25em;
            block-size: 1em;
            """;
        }
    }

    /** What of a menu item's leading place is not shown: its own icon while it is checked, its check while it is not (23 Oct). */
    public record el_menu_item_off() implements CssClass<ElementStyles> {
        @Override public String body() { return "display: none;"; }
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

    // ── the status footer and the expandable footer area (23 Oct): placed here, their looks the house's ──

    /** The status footer: what it shows on one row, its cover over it. */
    public record el_status_footer() implements CssClass<ElementStyles> {
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
    public record el_status_room() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-width: 0;
            overflow: hidden;
            """;
        }
    }

    /** Over what it shows, unseen: the hand lands on it, never on what is beneath - read-only, still read aloud. */
    public record el_status_cover() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            position: absolute;
            inset: 0;
            """;
        }
    }

    /** The expandable footer area: its status footer at its foot, its area standing on it. */
    public record el_footer_area() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            position: relative;
            flex: none;
            """;
        }
    }

    /** Its area: over what is above it, as wide as it, as tall as its widget asks, up to most of the page. */
    public record el_footer_area_panel() implements CssClass<ElementStyles> {
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
    public record el_footer_area_closed() implements CssClass<ElementStyles> {
        @Override public String body() { return """
            display: none;
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
                       new el_slider(), new el_slider_label(), new el_slider_range(), new el_slider_readout(),
                       new el_menu_item(), new el_menu_item_label(), new el_menu_item_hint(), new el_menu_item_disclose(), new el_menu_item_lead(), new el_menu_item_off(), new el_choice_option(), new el_choice_option_note(),
                       new el_card(), new el_summary(), new el_summary_head(), new el_summary_title(), new el_summary_tag(), new el_summary_text(), new el_summary_foot(),
                       new el_checkbox(), new el_pane_thumb_name(), new el_switch(), new el_switch_groove(), new el_switch_knob(),
                       new el_status_footer(), new el_status_room(), new el_status_cover(), new el_footer_area(), new el_footer_area_panel(), new el_footer_area_closed(),
                       new el_site_bar(), new el_site_bar_start(), new el_site_bar_way(), new el_site_bar_end(),
                       new el_brand(), new el_brand_mark(), new el_trail(), new el_trail_crumb());
    }
}
