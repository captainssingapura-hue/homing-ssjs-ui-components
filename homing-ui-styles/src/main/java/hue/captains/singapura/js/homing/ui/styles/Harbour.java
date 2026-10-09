package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;
import java.util.Map;

import static hue.captains.singapura.js.homing.ui.styles.Say.BUTTON;
import static hue.captains.singapura.js.homing.ui.styles.Say.CONTROL;
import static hue.captains.singapura.js.homing.ui.styles.Say.ROOT;
import static hue.captains.singapura.js.homing.ui.styles.Say.rest;
import static hue.captains.singapura.js.homing.ui.styles.Say.when;

/**
 * Harbour, the house colours: navy and gold on a cool white page, light. Every semantic colour at
 * the root, each said whole; the ring a control wears on focus and under the keys; a box's shadow
 * cast in the house navy, lit white, glowing navy; and each button's rank - its background, its
 * ink, its border. A coloured button's background and ink move with its extent: the word at 1, the
 * neutral at 0, the meaning turned the other way at -1.
 */
public record Harbour() implements ColorPalette {

    public static final Harbour INSTANCE = new Harbour();

    @Override public List<Implementation<? extends Target.Color>> implementations() { return ANSWERS; }

    private static final Target.Color.Background BACKGROUND = Target.Color.Background.INSTANCE;
    private static final Target.Color.Ink INK = Target.Color.Ink.INSTANCE;
    private static final Target.Color.Border BORDER = Target.Color.Border.INSTANCE;

    /** A ground and nothing drawn on it: the background's whole set, its ramp and its marks transparent. */
    private static String ground(String colour) {
        return "background-color: " + colour + "; --background-ramp-from: transparent; --background-ramp-via: transparent;"
                + " --background-ramp-to: transparent; --background-mark: transparent;";
    }

    /** Between the word at full extent and what it is at 0, then the other way below 0. */
    private static String extent(String word, String below) {
        return "color-mix(in srgb, " + word + " calc(clamp(0, var(--extent), 1) * 100%), color-mix(in srgb, " + below + "))";
    }

    private static final List<Implementation<? extends Target.Color>> ANSWERS = List.of(
            // ── the roots: every semantic colour, said whole ──
            rest(ROOT, BACKGROUND, ground("transparent")),
            rest(ROOT, INK, "color: inherit;"),
            rest(ROOT, BORDER, "border-color: currentColor;"),
            rest(ROOT, Target.Color.Outline.INSTANCE, "outline-color: currentColor;"),
            rest(ROOT, Target.Color.ColumnRule.INSTANCE, "column-rule-color: currentColor;"),
            rest(ROOT, Target.Color.Fill.INSTANCE, "fill: currentColor;"),
            rest(ROOT, Target.Color.Stroke.INSTANCE, "stroke: none;"),
            rest(ROOT, Target.Color.Scrollbar.INSTANCE, "scrollbar-color: auto;"),
            rest(ROOT, Target.Color.BoxShadow.INSTANCE, "--box-shadow-cast: #111936; --box-shadow-lit: #FFFFFF; --box-shadow-glow: #1E2761;"),
            // ── the ring: on focus, and the keys' marks - offered, lent, held ──
            when(CONTROL, Target.Color.Outline.INSTANCE, Map.of(
                    State.FOCUS, "outline-color: color-mix(in srgb, #F4B942 55%, #E2E8F0);",
                    State.CANDIDATE, "outline-color: color-mix(in srgb, #F4B942 45%, transparent);",
                    State.LENT, "outline-color: color-mix(in srgb, #F4B942 25%, transparent);",
                    State.HELD, "outline-color: #F4B942;")),
            // ── each button's rank ──
            rest(BUTTON, BORDER, "border-color: #E2E8F0;"),
            rest(HouseControls.PlainButton.INSTANCE, BACKGROUND, ground("#FFFFFF")),
            rest(HouseControls.PlainButton.INSTANCE, INK, "color: #3B4A6B;"),
            rest(HouseControls.PrimaryButton.INSTANCE, BACKGROUND,
                    ground(extent("#1E2761", "#E2E8F0 calc((1 + clamp(-1, var(--extent), 0)) * 100%), transparent"))),
            rest(HouseControls.PrimaryButton.INSTANCE, INK, "color: color-mix(in srgb, #FFFFFF calc(clamp(0, var(--extent), 1) * 100%), #3B4A6B);"),
            rest(HouseControls.PrimaryButton.INSTANCE, BORDER, "border-color: #1E2761;"),
            rest(HouseControls.SecondaryButton.INSTANCE, BACKGROUND,
                    ground(extent("#F4B942", "#E2E8F0 calc((1 + clamp(-1, var(--extent), 0)) * 100%), transparent"))),
            rest(HouseControls.SecondaryButton.INSTANCE, INK, "color: #111936;"),
            rest(HouseControls.SecondaryButton.INSTANCE, BORDER, "border-color: #C8921E;"),
            rest(HouseControls.DangerButton.INSTANCE, BACKGROUND,
                    ground(extent("rgba(220, 38, 38, .10)", "rgba(34, 139, 34, .12) calc(clamp(0, calc(-1 * var(--extent)), 1) * 100%), transparent"))),
            rest(HouseControls.DangerButton.INSTANCE, INK,
                    "color: color-mix(in srgb, #7F1D1D calc(clamp(0, var(--extent), 1) * 100%),"
                    + " color-mix(in srgb, #1B5E20 calc(clamp(0, calc(-1 * var(--extent)), 1) * 100%), #3B4A6B));"),
            rest(HouseControls.DangerButton.INSTANCE, BORDER, "border-color: rgba(220, 38, 38, .35);"),
            rest(HouseControls.WarningButton.INSTANCE, BACKGROUND,
                    ground(extent("rgba(202, 138, 4, .12)", "rgba(34, 139, 34, .12) calc(clamp(0, calc(-1 * var(--extent)), 1) * 100%), transparent"))),
            rest(HouseControls.WarningButton.INSTANCE, INK, "color: #713F12;"),
            rest(HouseControls.SuccessButton.INSTANCE, BACKGROUND,
                    ground(extent("rgba(34, 139, 34, .12)", "rgba(220, 38, 38, .10) calc(clamp(0, calc(-1 * var(--extent)), 1) * 100%), transparent"))),
            rest(HouseControls.SuccessButton.INSTANCE, INK, "color: #1B5E20;"));
}
