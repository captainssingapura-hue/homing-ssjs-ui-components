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
import static hue.captains.singapura.js.homing.ui.styles.Say.sliderMeasure;
import static hue.captains.singapura.js.homing.ui.styles.Say.sliderShape;
import static hue.captains.singapura.js.homing.ui.styles.Say.currentKnob;
import static hue.captains.singapura.js.homing.ui.styles.Say.sliderMarks;
import static hue.captains.singapura.js.homing.ui.styles.Say.menuFrame;
import static hue.captains.singapura.js.homing.ui.styles.Say.menuRow;
import static hue.captains.singapura.js.homing.ui.styles.Say.menuShadow;

/**
 * Neumorphism: moulded out of the page's own clay - no rule, its depth all in light and shade,
 * pressed in when pressed. It draws no line, so says nothing of the rule: the base's is none.
 */
public record Neumorphism() implements DesignStyle, Derives<Base> {

    /** Its faces: the body's, and the display's for what is set apart. */
    private static final String BODY = "\"Nunito\", \"Segoe UI\", system-ui, sans-serif";
    private static final String DISPLAY = "\"Nunito\", \"Quicksand\", \"Varela Round\", \"Segoe UI\", system-ui, sans-serif";

    public static final Neumorphism INSTANCE = new Neumorphism();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    private static final String DARK = "color-mix(in srgb, var(--box-shadow-cast) 16%, transparent)";
    private static final String LIGHT = "var(--box-shadow-lit)";

    /** The press: the clay pressed in. It does not move. */
    private static final String PRESS_SHADOW = "box-shadow: inset 4px 4px 8px " + DARK + ", inset -4px -4px 8px " + LIGHT + ";";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 12px;"),
                    rest(BUTTON, FACE, "font-family: \"Nunito\", \"Quicksand\", \"Varela Round\", \"Segoe UI\", system-ui, sans-serif;"),
                    rest(BUTTON, EASE, "transition: box-shadow 180ms ease, transform 180ms ease, background-color 180ms ease, color 120ms ease;"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: 5px 5px 10px " + DARK + ", -5px -5px 10px " + LIGHT + ";",
                            State.HOVER, "box-shadow: 7px 7px 14px " + DARK + ", -7px -7px 14px " + LIGHT + ";",
                            State.ACTIVE, PRESS_SHADOW)),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-1px);")),
                    rings("2px solid -2px", "2px dotted 4px", "2px solid -3px", "3px solid -3px"),
                    closeMark("✗")),
            held(PRESS_SHADOW, null),
            bareMarks(),
            // ── its type: the body face on every text, its display face where a text is set apart ──
            type(TEXT, BODY, null, null),
            type(HEADING, DISPLAY, "800", null),
            type(KICKER, DISPLAY, "800", "letter-spacing: 0.12em; text-transform: uppercase;"),
            type(LABEL, null, "700", null),
            type(READOUT, DISPLAY, "900", "font-variant-numeric: tabular-nums;"),
            type(WORDMARK, DISPLAY, null, "letter-spacing: 0.02em;"),
            // ── the veil behind a dialog: soft ──
            veil("blur(6px)"),
            measure("10px", "20px", "8px", "64px", "1.3"),
            // ── its slider a soft unruled channel, the knob a round disc ruled with a hairline - flat, so the line is what shows it ──
            sliderMeasure("220px", "28px", "8px", "22px", "16px", "30px"),
            sliderShape("999px", "0", "50%", "1px", "6px"),
            sliderMarks("2px", "10px", "1px", "6px"),
            currentKnob("2px solid -2px"),
            // ── its menu moulded out of the page ──
            menuFrame("14px", "0", "8px", "2px", "210px"),
            menuRow("10px", "8px", "14px"),
            menuShadow("14px 14px 28px color-mix(in srgb, var(--box-shadow-cast) 16%, transparent), -14px -14px 28px var(--box-shadow-lit)"),
            disabled(".5", "not-allowed"));
}
