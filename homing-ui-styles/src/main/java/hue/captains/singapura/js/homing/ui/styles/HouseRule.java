package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.semantic.Answer;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

import java.util.List;
import java.util.Map;

import static hue.captains.singapura.js.homing.ui.styles.Say.BADGE;
import static hue.captains.singapura.js.homing.ui.styles.Say.BUTTON;
import static hue.captains.singapura.js.homing.ui.styles.Say.CAPTION;
import static hue.captains.singapura.js.homing.ui.styles.Say.HEADING;
import static hue.captains.singapura.js.homing.ui.styles.Say.LINK;
import static hue.captains.singapura.js.homing.ui.styles.Say.SEPARATOR;
import static hue.captains.singapura.js.homing.ui.styles.Say.BOUNDARY_LINE;
import static hue.captains.singapura.js.homing.ui.styles.Say.GROUND;
import static hue.captains.singapura.js.homing.ui.styles.Say.SITE_BAR;
import static hue.captains.singapura.js.homing.ui.styles.Say.EDGE_STRIP;
import static hue.captains.singapura.js.homing.ui.styles.Say.FLOATING_PANE;
import static hue.captains.singapura.js.homing.ui.styles.Say.PANE;
import static hue.captains.singapura.js.homing.ui.styles.Say.TITLE_BAR;
import static hue.captains.singapura.js.homing.ui.styles.Say.FLOAT_LAYER;
import static hue.captains.singapura.js.homing.ui.styles.Say.DELIMITER;
import static hue.captains.singapura.js.homing.ui.styles.Say.KICKER;
import static hue.captains.singapura.js.homing.ui.styles.Say.LEDE;
import static hue.captains.singapura.js.homing.ui.styles.Say.CONTEXT_MENU;
import static hue.captains.singapura.js.homing.ui.styles.Say.DETENT;
import static hue.captains.singapura.js.homing.ui.styles.Say.DROP_MARK;
import static hue.captains.singapura.js.homing.ui.styles.Say.BRAND;
import static hue.captains.singapura.js.homing.ui.styles.Say.PREFERENCES_BUTTON;
import static hue.captains.singapura.js.homing.ui.styles.Say.CARD;
import static hue.captains.singapura.js.homing.ui.styles.Say.PANEL;
import static hue.captains.singapura.js.homing.ui.styles.Say.PANEL_HEAD;
import static hue.captains.singapura.js.homing.ui.styles.Say.SLIDER_GROUP;
import static hue.captains.singapura.js.homing.ui.styles.Say.ACTION_BAR;
import static hue.captains.singapura.js.homing.ui.styles.Say.TRAIL;
import static hue.captains.singapura.js.homing.ui.styles.Say.RANGE;
import static hue.captains.singapura.js.homing.ui.styles.Say.LAMP;
import static hue.captains.singapura.js.homing.ui.styles.Say.TAB;
import static hue.captains.singapura.js.homing.ui.styles.Say.TAB_STRIP;
import static hue.captains.singapura.js.homing.ui.styles.Say.MENU_ITEM;
import static hue.captains.singapura.js.homing.ui.styles.Say.ITEM;
import static hue.captains.singapura.js.homing.ui.styles.Say.CHOICE_OPTION;
import static hue.captains.singapura.js.homing.ui.styles.Say.LIST_ROW;
import static hue.captains.singapura.js.homing.ui.styles.Say.FILL;
import static hue.captains.singapura.js.homing.ui.styles.Say.GROOVE;
import static hue.captains.singapura.js.homing.ui.styles.Say.KNOB;
import static hue.captains.singapura.js.homing.ui.styles.Say.PANE_THUMB;
import static hue.captains.singapura.js.homing.ui.styles.Say.TICK;
import static hue.captains.singapura.js.homing.ui.styles.Say.PILL;
import static hue.captains.singapura.js.homing.ui.styles.Say.CONTROL;
import static hue.captains.singapura.js.homing.ui.styles.Say.ROOT;
import static hue.captains.singapura.js.homing.ui.styles.Say.rest;
import static hue.captains.singapura.js.homing.ui.styles.Say.when;

/**
 * The house rule: a palette written from its {@link Seeds}, so every house palette says the same
 * semantic colours in the same places and differs only in what they are. Every semantic colour at
 * the root, each said whole; a box's shadow cast in the inverted layer, lit in the raised one,
 * glowing in the title colour; the ring of attention in the accent; a button's face - raised, in
 * the text's ink, a hairline round it - a toggle that is on tinted with the accent; a close turning to
 * danger under the hand, an icon button washed in its own ink; and each
 * coloured button's rank - its background, its ink, its border; a pane's thumbnail raised and
 * hairlined, the current one tinted and edged with the accent; a tab tinted with the accent, the chosen one on the title
 * colour, and its strip raised and ruled in the accent; a switch's groove the accent when it is on. A coloured button's background and ink move with its extent:
 * the word at 1, the neutral at 0, the meaning turned the other way at -1. Danger, warning and
 * success are the same in every house palette; only the ink they fall back to is the palette's.
 */
final class HouseRule {

    private HouseRule() {}

    private static final Target.Color.Background BACKGROUND = Target.Color.Background.INSTANCE;
    private static final Target.Color.Ink INK = Target.Color.Ink.INSTANCE;
    private static final Target.Color.Border BORDER = Target.Color.Border.INSTANCE;

    /** Danger said whole, for a close under the hand: the same in every house palette, as every feedback colour is. */
    private static final String DANGER = "#DC2626";
    private static final String DANGER_PRESSED = "#B91C1C";
    private static final String ON_DANGER = "#FFFFFF";

    /** A flat hover for a mark alone: a wash of the ink it is drawn in, so it shows on whatever holds it, light or dark. */
    private static final String WASH = "color-mix(in srgb, currentColor 12%, transparent)";

    /** A ground and nothing drawn on it: the background's whole set, its ramp and its marks transparent. */
    private static String ground(String colour) {
        return "background-color: " + colour + "; --background-ramp-from: transparent; --background-ramp-via: transparent;"
                + " --background-ramp-to: transparent; --background-mark: transparent;";
    }

    /** Between the word at full extent and what it is at 0, then the other way below 0. */
    private static String extent(String word, String below) {
        return "color-mix(in srgb, " + word + " calc(clamp(0, var(--extent), 1) * 100%), color-mix(in srgb, " + below + "))";
    }

    /** The palette the seeds make. */
    static List<Implementation<? extends Target.Color>> answers(Seeds s) {
        String toBorder = s.border() + " calc((1 + clamp(-1, var(--extent), 0)) * 100%), transparent";
        // quieter than the ink of whatever it is set on, so it reads on a light surface and on an inverted one - and
        // still at 4.5:1 or more on the house's light pages, as small text must be
        String quiet = "color-mix(in srgb, currentColor 80%, transparent)";
        return List.of(
                // ── the roots: every semantic colour, said whole ──
                rest(ROOT, BACKGROUND, ground("transparent")),
                rest(ROOT, INK, "color: inherit;"),
                rest(ROOT, BORDER, "border-color: currentColor;"),
                rest(ROOT, Target.Color.Outline.INSTANCE, "outline-color: currentColor;"),
                rest(ROOT, Target.Color.ColumnRule.INSTANCE, "column-rule-color: currentColor;"),
                rest(ROOT, Target.Color.Fill.INSTANCE, "fill: currentColor;"),
                rest(ROOT, Target.Color.Stroke.INSTANCE, "stroke: none;"),
                rest(ROOT, Target.Color.Scrollbar.INSTANCE, "scrollbar-color: auto;"),
                rest(ROOT, Target.Color.BoxShadow.INSTANCE,
                        "--box-shadow-cast: " + s.inverted() + "; --box-shadow-lit: " + s.raised() + "; --box-shadow-glow: " + s.title() + ";"),
                // ── the page's ground: the palette's ground, in the text's ink - which every text on the page takes, standing on it - and
                //    its scrollbar the ink faded into the ground; the site's bar over it inverted - the ground and the ink changed
                //    places: the inverted layer its ground, the page's ground its ink - the accent ruled along its foot, as the tab strip's is ──
                rest(GROUND, BACKGROUND, ground(s.ground())),
                rest(GROUND, INK, "color: " + s.text() + ";"),
                rest(GROUND, Target.Color.Scrollbar.INSTANCE, "scrollbar-color: color-mix(in srgb, " + s.text() + " 55%, " + s.ground() + ") " + s.ground() + ";"),
                rest(SITE_BAR, BACKGROUND, ground(s.inverted())),
                rest(SITE_BAR, INK, "color: " + s.ground() + ";"),
                rest(SITE_BAR, BORDER, "border-color: " + s.accent() + ";"),
                // ── an edge strip, laid over its host's foot: raised, in the text's ink, a hairline along its top ──
                rest(EDGE_STRIP, BACKGROUND, ground(s.raised())),
                rest(EDGE_STRIP, INK, "color: " + s.text() + ";"),
                rest(EDGE_STRIP, BORDER, "border-color: " + s.border() + ";"),
                // ── the desk panes float over, recessed below the page; a pane that floats - a dialog's window is one: raised, in the text's ink, a hairline round it, the accent's edge
                //    while it is the active one; its title bar inverted as the site's bar is, a hairline under it, the title's
                //    colour while it is in the hand ──
                rest(FLOAT_LAYER, BACKGROUND, ground(s.recessed())),
                // ── a pane: the page's ground, the text's ink; ringed in the accent while the keys are on it - in full held, fainter
                //    offered and lent - and washed with it, ringed, while a tab is offered to it ──
                when(PANE, BACKGROUND, Map.of(State.REST, ground(s.ground()),
                        State.HIGHLIGHTED, ground("color-mix(in srgb, " + s.accent() + " 10%, " + s.ground() + ")"))),
                rest(PANE, INK, "color: " + s.text() + ";"),
                when(PANE, Target.Color.Outline.INSTANCE, Map.of(
                        State.HELD, "outline-color: " + s.accent() + ";",
                        State.CANDIDATE, "outline-color: color-mix(in srgb, " + s.accent() + " 45%, transparent);",
                        State.LENT, "outline-color: color-mix(in srgb, " + s.accent() + " 25%, transparent);",
                        State.HIGHLIGHTED, "outline-color: " + s.accent() + ";")),
                // a pane that floats is lit from its own head, never washed: raised in every state
                when(FLOATING_PANE, BACKGROUND, Map.of(State.REST, ground(s.raised()), State.HIGHLIGHTED, ground(s.raised()))),
                rest(FLOATING_PANE, INK, "color: " + s.text() + ";"),
                when(FLOATING_PANE, BORDER, Map.of(
                        State.REST, "border-color: " + s.border() + ";",
                        State.CURRENT, "border-color: color-mix(in srgb, " + s.accent() + " 55%, " + s.border() + ");")),
                when(TITLE_BAR, BACKGROUND, Map.of(State.REST, ground(s.inverted()), State.DRAGGING, ground(s.title()))),
                when(TITLE_BAR, INK, Map.of(State.REST, "color: " + s.ground() + ";", State.DRAGGING, "color: " + s.onInverted() + ";")),
                rest(TITLE_BAR, BORDER, "border-color: " + s.border() + ";"),
                // ── the ring: on focus, and the keys' marks - offered, lent, held ──
                when(CONTROL, Target.Color.Outline.INSTANCE, Map.of(
                        State.FOCUS, "outline-color: color-mix(in srgb, " + s.accent() + " 55%, " + s.border() + ");",
                        State.CANDIDATE, "outline-color: color-mix(in srgb, " + s.accent() + " 45%, transparent);",
                        State.LENT, "outline-color: color-mix(in srgb, " + s.accent() + " 25%, transparent);",
                        State.HELD, "outline-color: " + s.accent() + ";")),
                // ── a button's face: raised, in the text's ink, a hairline round it - every button's but a coloured one's ──
                rest(BUTTON, BACKGROUND, ground(s.raised())),
                rest(BUTTON, INK, "color: " + s.text() + ";"),
                rest(BUTTON, BORDER, "border-color: " + s.border() + ";"),
                // ── a mark alone - a close button's cross, an icon button's icon: no ground of its own, in the ink of what holds it;
                //    under the hand a close turns to danger, as a window's does, and an icon button is washed in the ink it is drawn in; off, neither ──
                when(HouseControls.CloseButton.INSTANCE, BACKGROUND, Map.of(
                        State.REST, ground("transparent"),
                        State.HOVER, ground(DANGER),
                        State.DISABLED, ground("transparent"),
                        State.ACTIVE, ground(DANGER_PRESSED))),
                when(HouseControls.CloseButton.INSTANCE, INK, Map.of(
                        State.REST, "color: inherit;",
                        State.HOVER, "color: " + ON_DANGER + ";",
                        State.DISABLED, "color: inherit;",
                        State.ACTIVE, "color: " + ON_DANGER + ";")),
                when(HouseControls.IconButton.INSTANCE, BACKGROUND, Map.of(
                        State.REST, ground("transparent"),
                        State.HOVER, ground(WASH),
                        State.DISABLED, ground("transparent"))),
                rest(HouseControls.IconButton.INSTANCE, INK, "color: inherit;"),
                // ── a toggle that is on: tinted with the accent, edged with it ──
                when(HouseControls.ToggleButton.INSTANCE, BACKGROUND,
                        Map.of(State.PRESSED, ground("color-mix(in srgb, " + s.accent() + " 22%, " + s.raised() + ")"))),
                when(HouseControls.ToggleButton.INSTANCE, BORDER, Map.of(State.PRESSED, "border-color: " + s.accentEdge() + ";")),
                // ── each coloured button's rank ──
                rest(HouseControls.PrimaryButton.INSTANCE, BACKGROUND, ground(extent(s.title(), toBorder))),
                rest(HouseControls.PrimaryButton.INSTANCE, INK,
                        "color: color-mix(in srgb, " + s.onInverted() + " calc(clamp(0, var(--extent), 1) * 100%), " + s.text() + ");"),
                rest(HouseControls.PrimaryButton.INSTANCE, BORDER, "border-color: " + s.title() + ";"),
                rest(HouseControls.SecondaryButton.INSTANCE, BACKGROUND, ground(extent(s.accent(), toBorder))),
                rest(HouseControls.SecondaryButton.INSTANCE, INK, "color: " + s.onAccent() + ";"),
                rest(HouseControls.SecondaryButton.INSTANCE, BORDER, "border-color: " + s.accentEdge() + ";"),
                // ── feedback: the same in every house palette ──
                rest(HouseControls.DangerButton.INSTANCE, BACKGROUND,
                        ground(extent("rgba(220, 38, 38, .10)", "rgba(34, 139, 34, .12) calc(clamp(0, calc(-1 * var(--extent)), 1) * 100%), transparent"))),
                rest(HouseControls.DangerButton.INSTANCE, INK,
                        "color: color-mix(in srgb, #7F1D1D calc(clamp(0, var(--extent), 1) * 100%),"
                        + " color-mix(in srgb, #1B5E20 calc(clamp(0, calc(-1 * var(--extent)), 1) * 100%), " + s.text() + "));"),
                rest(HouseControls.DangerButton.INSTANCE, BORDER, "border-color: rgba(220, 38, 38, .35);"),
                rest(HouseControls.WarningButton.INSTANCE, BACKGROUND,
                        ground(extent("rgba(202, 138, 4, .12)", "rgba(34, 139, 34, .12) calc(clamp(0, calc(-1 * var(--extent)), 1) * 100%), transparent"))),
                rest(HouseControls.WarningButton.INSTANCE, INK, "color: #713F12;"),
                rest(HouseControls.SuccessButton.INSTANCE, BACKGROUND,
                        ground(extent("rgba(34, 139, 34, .12)", "rgba(220, 38, 38, .10) calc(clamp(0, calc(-1 * var(--extent)), 1) * 100%), transparent"))),
                rest(HouseControls.SuccessButton.INSTANCE, INK, "color: #1B5E20;"),
                // ── text: a plain text - a label, a readout, a wordmark - in the ink of what it is set on, the root's,
                //    as a floating pane's title stands on its inverted head and the site's name on its bar; a heading in
                //    the title's and a kicker in the accent's, each blended into that ink, so either reads on a light
                //    surface or a dark one (the house's palettes have no dark mode of their own); a lede and a caption
                //    quieter than it; a badge and a pill on a chip of their own ──
                rest(HEADING, INK, "color: color-mix(in srgb, " + s.title() + " 50%, currentColor);"),
                rest(KICKER, INK, "color: color-mix(in srgb, " + s.accentEdge() + " 50%, currentColor);"),
                rest(LEDE, INK, "color: " + quiet + ";"),
                rest(CAPTION, INK, "color: " + quiet + ";"),
                // ── a link: quieter than the ink it stands in, and in the title's blended into it under the hand; a crumb
                //    in the ink of the trail it stands in, the root's ──
                when(LINK, INK, Map.of(State.REST, "color: " + quiet + ";",
                                       State.HOVER, "color: color-mix(in srgb, " + s.title() + " 50%, currentColor);")),
                // ── the marks between things, each in the ink of what it stands in, so it follows the theme the menu or the
                //    trail wears, light or dark: a separator that ink faded to a line; a delimiter quieter than the ink of
                //    the trail, a sign rather than a word ──
                rest(SEPARATOR, BORDER, "border-color: color-mix(in srgb, currentColor 30%, transparent);"),
                rest(DELIMITER, INK, "color: color-mix(in srgb, currentColor 60%, transparent);"),
                // ── a layout's boundary: the line two regions meet at, in the palette's edge, whether the hand may move it
                //    or not; where it may - a splitter - lit with the accent part of the way under the hand, and at full
                //    while the hand has it or the keys are on it: its colour alone changes ──
                when(BOUNDARY_LINE, BACKGROUND, Map.of(
                        State.REST, ground(s.border()),
                        State.HOVER, ground("color-mix(in srgb, " + s.accent() + " 50%, " + s.border() + ")"),
                        State.FOCUS, ground(s.accent()),
                        State.DRAGGING, ground(s.accent()))),
                rest(BADGE, BACKGROUND, ground("color-mix(in srgb, " + s.accent() + " 18%, transparent)")),
                rest(PILL, BACKGROUND, ground("color-mix(in srgb, currentColor 10%, transparent)")),
                // ── a pane's thumbnail: raised, a hairline round it; the current one - the cursor's - tinted with the accent and edged with it ──
                when(PANE_THUMB, BACKGROUND, Map.of(
                        State.REST, ground(s.raised()),
                        State.CURRENT, ground("color-mix(in srgb, " + s.accent() + " 12%, " + s.raised() + ")"))),
                when(PANE_THUMB, BORDER, Map.of(
                        State.REST, "border-color: " + s.border() + ";",
                        State.CURRENT, "border-color: " + s.accent() + ";")),
                // ── a slider, flat: its groove a wash of the ink it stands in, edged quieter than that ink; the fill the accent; a tick
                //    in that ink, faded; the detent - where it rests - shown only while the knob is dragged; the knob raised, in the
                //    ink of what holds it, edged with the accent while dragged, and ringed as a focused control when it is a group's current one ──
                when(GROOVE, BACKGROUND, Map.of(
                        State.REST, ground("color-mix(in srgb, currentColor 10%, transparent)"),
                        State.CHECKED, ground(s.accent()))),
                when(GROOVE, BORDER, Map.of(
                        State.REST, "border-color: color-mix(in srgb, currentColor 45%, transparent);",
                        State.CHECKED, "border-color: " + s.accent() + ";")),
                rest(FILL, BACKGROUND, ground(s.accent())),
                rest(TICK, BACKGROUND, ground("color-mix(in srgb, currentColor 60%, transparent)")),
                when(DETENT, BACKGROUND, Map.of(
                        State.REST, ground("transparent"),
                        State.DRAGGING, ground("color-mix(in srgb, currentColor 60%, transparent)"))),
                rest(KNOB, BACKGROUND, ground(s.raised())),
                when(KNOB, BORDER, Map.of(State.DRAGGING, "border-color: " + s.accent() + ";")),
                when(KNOB, Target.Color.Outline.INSTANCE, Map.of(
                        State.CURRENT, "outline-color: color-mix(in srgb, " + s.accent() + " 55%, " + s.border() + ");")),
                // ── a context menu: raised, in the text's ink, a hairline round it; its rows items (below) ──
                rest(CONTEXT_MENU, BACKGROUND, ground(s.raised())),
                rest(CONTEXT_MENU, INK, "color: " + s.text() + ";"),
                rest(CONTEXT_MENU, BORDER, "border-color: " + s.border() + ";"),
                // ── a tab: tinted with the accent, more under the hand; the chosen one on the title colour, in the ink written on it and
                //    edged with it. Its strip raised, ruled along its foot in the accent, and lit with it while the work is in its pane;
                //    where a dragged tab would land, a bar of the accent; the mark that says the keys are in a tab, in the accent as
                //    far as its extent - part of the way while the cursor is on the strip, at full while the keys are in the tab ──
                when(TAB, BACKGROUND, Map.of(
                        State.REST, ground("color-mix(in srgb, " + s.accent() + " 8%, transparent)"),
                        State.HOVER, ground("color-mix(in srgb, " + s.accent() + " 15%, transparent)"),
                        State.SELECTED, ground(s.title()))),
                when(TAB, INK, Map.of(State.REST, "color: " + s.text() + ";", State.SELECTED, "color: " + s.onInverted() + ";")),
                when(TAB, BORDER, Map.of(State.REST, "border-color: " + s.border() + ";", State.SELECTED, "border-color: " + s.title() + ";")),
                when(TAB_STRIP, BACKGROUND, Map.of(
                        State.REST, ground(s.raised()),
                        State.CURRENT, ground("color-mix(in srgb, " + s.accent() + " 7%, " + s.raised() + ")"))),
                rest(TAB_STRIP, BORDER, "border-color: " + s.accent() + ";"),
                rest(DROP_MARK, BACKGROUND, ground(s.accent())),
                // ── a brand: its colour - the accent - its mark's ground, its name in the ink of the bar it stands on; a preferences
                //    button bare, in that ink and outlined in it, washed in it under the hand ──
                rest(BRAND, BACKGROUND, ground(s.accent())),
                rest(BRAND, INK, "color: inherit;"),
                when(PREFERENCES_BUTTON, BACKGROUND, Map.of(State.REST, ground("transparent"), State.HOVER, ground(WASH))),
                rest(PREFERENCES_BUTTON, INK, "color: inherit;"),
                rest(PREFERENCES_BUTTON, BORDER, "border-color: currentColor;"),
                // ── a card, and a group of sliders framed as one: raised, a hairline round it ──
                rest(CARD, BACKGROUND, ground(s.raised())),
                rest(CARD, BORDER, "border-color: " + s.border() + ";"),
                rest(SLIDER_GROUP, BACKGROUND, ground(s.raised())),
                rest(SLIDER_GROUP, BORDER, "border-color: " + s.border() + ";"),
                // ── a panel: raised, in the text's ink, a hairline round it - the accent's while it is the current one; the line
                //    under its head in the accent, as a tab strip's foot is ──
                rest(PANEL, BACKGROUND, ground(s.raised())),
                rest(PANEL, INK, "color: " + s.text() + ";"),
                when(PANEL, BORDER, Map.of(State.REST, "border-color: " + s.border() + ";", State.CURRENT, "border-color: " + s.accent() + ";")),
                rest(PANEL_HEAD, BORDER, "border-color: " + s.accent() + ";"),
                // ── each ringed as a control is, in the ring's colours: a card with an action, a group of sliders holding the keys ──
                new Implementation<>(CARD, Target.Color.Outline.INSTANCE, new Answer.Redirect<>(CONTROL)),
                new Implementation<>(SLIDER_GROUP, Target.Color.Outline.INSTANCE, new Answer.Redirect<>(CONTROL)),
                // ── a row of actions: raised, capped off by a hairline; a trail quieter than the bar it stands on ──
                rest(ACTION_BAR, BACKGROUND, ground(s.raised())),
                rest(ACTION_BAR, BORDER, "border-color: " + s.border() + ";"),
                rest(TRAIL, INK, "color: color-mix(in srgb, currentColor 80%, transparent);"),
                // ── a range: the browser's own slider, drawn in the ink it is set in - the accent - on no ground of its own ──
                rest(RANGE, BACKGROUND, ground("transparent")),
                rest(RANGE, INK, "color: " + s.accent() + ";"),
                // ── a lamp: quiet at rest; lit - the current one - tinted and edged with the accent, in the text's ink; broken - invalid -
                //    the danger colour, which outranks lit ──
                when(LAMP, BACKGROUND, Map.of(
                        State.REST, ground("transparent"),
                        State.CURRENT, ground("color-mix(in srgb, " + s.accent() + " 12%, transparent)"),
                        State.INVALID, ground(DANGER))),
                when(LAMP, INK, Map.of(
                        State.REST, "color: color-mix(in srgb, currentColor 70%, transparent);",
                        State.CURRENT, "color: " + s.text() + ";",
                        State.INVALID, "color: " + ON_DANGER + ";")),
                when(LAMP, BORDER, Map.of(
                        State.REST, "border-color: transparent;",
                        State.CURRENT, "border-color: " + s.accent() + ";",
                        State.INVALID, "border-color: " + DANGER + ";")),
                // ── an item - a row of a list: clear at rest; the chosen one on the title colour, in the ink written on it, edged with
                //    it; the current one - a monitor's holder - tinted lightly with the accent; the one the cursor is on tinted more
                //    and edged with it; ringed as a control is ──
                when(ITEM, BACKGROUND, Map.of(
                        State.REST, ground("transparent"),
                        State.SELECTED, ground(s.title()),
                        State.CURRENT, ground("color-mix(in srgb, " + s.accent() + " 12%, transparent)"),
                        State.HIGHLIGHTED, ground("color-mix(in srgb, " + s.accent() + " 28%, transparent)"))),
                when(ITEM, INK, Map.of(State.SELECTED, "color: " + s.onInverted() + ";")),
                when(ITEM, BORDER, Map.of(
                        State.REST, "border-color: transparent;",
                        State.SELECTED, "border-color: " + s.title() + ";",
                        State.HIGHLIGHTED, "border-color: " + s.accent() + ";")),
                new Implementation<>(ITEM, Target.Color.Outline.INSTANCE, new Answer.Redirect<>(CONTROL)),
                // ── one a hand points at - a choice, a listed row, a menu's row - washed with the accent under it, hairlined ──
                when(CHOICE_OPTION, BACKGROUND, Map.of(State.HOVER, ground("color-mix(in srgb, " + s.accent() + " 15%, transparent)"))),
                when(CHOICE_OPTION, BORDER, Map.of(State.HOVER, "border-color: " + s.border() + ";")),
                when(LIST_ROW, BACKGROUND, Map.of(State.HOVER, ground("color-mix(in srgb, " + s.accent() + " 15%, transparent)"))),
                when(LIST_ROW, BORDER, Map.of(State.HOVER, "border-color: " + s.border() + ";")),
                when(MENU_ITEM, BACKGROUND, Map.of(State.HOVER, ground("color-mix(in srgb, " + s.accent() + " 15%, transparent)"))),
                when(MENU_ITEM, BORDER, Map.of(State.HOVER, "border-color: " + s.border() + ";")),
                // ── an icon as a symbol - a button's mark beside its word - in the ink it stands on, muted ──
                new Implementation<>(new Casting(HouseMarks.Icon.INSTANCE, HouseSaying.Symbol.INSTANCE), INK,
                        new Answer.Own<>("color: color-mix(in srgb, currentColor 75%, transparent);")),
                new Implementation<>(new Casting(HouseMarks.Icon.INSTANCE, HouseSaying.Keys.INSTANCE), INK,
                        new Answer.Own<>("color: color-mix(in srgb, " + s.accent() + " calc(clamp(0, var(--extent), 1) * 100%), currentColor);")));
    }
}
