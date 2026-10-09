package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;
import java.util.Map;

import static hue.captains.singapura.js.homing.ui.styles.Say.BUTTON;
import static hue.captains.singapura.js.homing.ui.styles.Say.CORNER;
import static hue.captains.singapura.js.homing.ui.styles.Say.EASE;
import static hue.captains.singapura.js.homing.ui.styles.Say.FACE;
import static hue.captains.singapura.js.homing.ui.styles.Say.BORDER;
import static hue.captains.singapura.js.homing.ui.styles.Say.SHADOW;
import static hue.captains.singapura.js.homing.ui.styles.Say.TRANSFORM;
import static hue.captains.singapura.js.homing.ui.styles.Say.answers;
import static hue.captains.singapura.js.homing.ui.styles.Say.bareMarks;
import static hue.captains.singapura.js.homing.ui.styles.Say.closeMark;
import static hue.captains.singapura.js.homing.ui.styles.Say.disabled;
import static hue.captains.singapura.js.homing.ui.styles.Say.held;
import static hue.captains.singapura.js.homing.ui.styles.Say.measure;
import static hue.captains.singapura.js.homing.ui.styles.Say.rest;
import static hue.captains.singapura.js.homing.ui.styles.Say.rings;
import static hue.captains.singapura.js.homing.ui.styles.Say.when;
import static hue.captains.singapura.js.homing.ui.styles.Say.type;
import static hue.captains.singapura.js.homing.ui.styles.Say.veil;
import static hue.captains.singapura.js.homing.ui.styles.Say.TEXT;
import static hue.captains.singapura.js.homing.ui.styles.Say.HEADING;
import static hue.captains.singapura.js.homing.ui.styles.Say.KICKER;
import static hue.captains.singapura.js.homing.ui.styles.Say.CAPTION;
import static hue.captains.singapura.js.homing.ui.styles.Say.LEDE;
import static hue.captains.singapura.js.homing.ui.styles.Say.LABEL;
import static hue.captains.singapura.js.homing.ui.styles.Say.READOUT;
import static hue.captains.singapura.js.homing.ui.styles.Say.WORDMARK;

/** Neo-futurism: a hairline, a lit rim that glows under the hand, a long eased glide. */
public record NeoFuturism() implements DesignStyle, Derives<Base> {

    /** Its faces: the body's, and the display's for what is set apart. */
    private static final String BODY = "\"Segoe UI Variable Text\", \"Segoe UI\", \"Inter\", system-ui, sans-serif";
    private static final String DISPLAY = "\"Bahnschrift\", \"Eurostile\", \"Avenir Next\", \"Futura\", \"Segoe UI\", sans-serif";

    public static final NeoFuturism INSTANCE = new NeoFuturism();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    /** The press: drawn in, the rim still lit. */
    private static final String PRESS_SHADOW = "box-shadow: 0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 60%, transparent),"
            + " 0 0 10px color-mix(in srgb, var(--box-shadow-glow) 25%, transparent);";
    private static final String PRESS_MOVE = "transform: scale(0.985);";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 2px;"),
                    rest(BUTTON, BORDER, "border-width: 1px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Bahnschrift\", \"Eurostile\", \"Avenir Next\", \"Futura\", \"Segoe UI\", sans-serif;"),
                    rest(BUTTON, EASE, "transition: transform 220ms cubic-bezier(0.2, 0.8, 0.2, 1), box-shadow 220ms cubic-bezier(0.2, 0.8, 0.2, 1),"
                            + " border-color 220ms ease, background-color 220ms ease, color 160ms ease;"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: 0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 14%, transparent), 0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent);",
                            State.HOVER, "box-shadow: 0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 60%, transparent), 0 0 28px color-mix(in srgb, var(--box-shadow-glow) 30%, transparent),"
                                    + " 0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent);",
                            State.ACTIVE, PRESS_SHADOW)),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-1px) scale(1.01);", State.ACTIVE, PRESS_MOVE)),
                    rings("1px solid -1px", "1px dashed 5px", "1px solid 3px", "1px solid 3px"),
                    closeMark("⨯")),
            held(PRESS_SHADOW, PRESS_MOVE),
            bareMarks(),
            // ── its type: the body face on every text, its display face where a text is set apart ──
            type(TEXT, BODY, null, null),
            type(HEADING, DISPLAY, "600", null),
            type(KICKER, DISPLAY, "600", "letter-spacing: 0.22em; text-transform: uppercase;"),
            type(LEDE, null, null, "letter-spacing: 0.01em;"),
            type(CAPTION, null, null, "letter-spacing: 0.02em;"),
            type(READOUT, DISPLAY, "300", "letter-spacing: 0.04em; font-variant-numeric: tabular-nums;"),
            type(WORDMARK, DISPLAY, null, "letter-spacing: 0.18em; text-transform: uppercase;"),
            // ── the veil behind a dialog: deep glass ──
            veil("blur(14px) saturate(1.4) brightness(0.7)"),
            measure("7px", "16px", "8px", "56px", "1.25"),
            disabled(".4", "not-allowed"));
}
