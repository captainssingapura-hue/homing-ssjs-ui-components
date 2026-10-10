package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;
import java.util.Map;

import static hue.captains.singapura.js.homing.ui.styles.Say.BADGE;
import static hue.captains.singapura.js.homing.ui.styles.Say.BUTTON;
import static hue.captains.singapura.js.homing.ui.styles.Say.CAPTION;
import static hue.captains.singapura.js.homing.ui.styles.Say.HEADING;
import static hue.captains.singapura.js.homing.ui.styles.Say.LINK;
import static hue.captains.singapura.js.homing.ui.styles.Say.SEPARATOR;
import static hue.captains.singapura.js.homing.ui.styles.Say.DELIMITER;
import static hue.captains.singapura.js.homing.ui.styles.Say.KICKER;
import static hue.captains.singapura.js.homing.ui.styles.Say.LEDE;
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
 * coloured button's rank - its background, its ink, its border. A coloured button's background and ink move with its extent:
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
                rest(BADGE, BACKGROUND, ground("color-mix(in srgb, " + s.accent() + " 18%, transparent)")),
                rest(PILL, BACKGROUND, ground("color-mix(in srgb, currentColor 10%, transparent)")));
    }
}
