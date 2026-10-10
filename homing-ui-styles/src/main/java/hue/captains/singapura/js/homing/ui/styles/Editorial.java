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
import static hue.captains.singapura.js.homing.ui.styles.Say.separatorRule;
import static hue.captains.singapura.js.homing.ui.styles.Say.waysEase;
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
import static hue.captains.singapura.js.homing.ui.styles.Say.markAlone;

/** Editorial, the house's own: a serif voice, a fine rule, a soft lift under the hand. */
public record Editorial() implements DesignStyle, Derives<Base> {

    /** Its faces: the body's, and the display's for what is set apart. */
    private static final String BODY = "\"Calibri\", \"Segoe UI\", system-ui, sans-serif";
    private static final String DISPLAY = "\"Georgia\", serif";

    public static final Editorial INSTANCE = new Editorial();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    /** The press: set down, its shadow drawn in. */
    private static final String PRESS_SHADOW = "box-shadow: 0 1px 2px color-mix(in srgb, var(--box-shadow-cast) 8%, transparent);";
    private static final String PRESS_MOVE = "transform: translateY(0);";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 3px;"),
                    rest(BUTTON, BORDER, "border-width: 1.5px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Georgia\", serif;"),
                    rest(BUTTON, EASE, "transition: transform 160ms ease, box-shadow 160ms ease, border-color 160ms ease;"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: 0 1px 3px color-mix(in srgb, var(--box-shadow-cast) 4%, transparent);",
                            State.HOVER, "box-shadow: 0 6px 16px color-mix(in srgb, var(--box-shadow-cast) 12%, transparent);",
                            State.ACTIVE, PRESS_SHADOW)),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-2px);", State.ACTIVE, PRESS_MOVE)),
                    rings("2px solid -2px", "1px dashed 3px", "1px solid -2px", "2px solid -2px"),
                    closeMark("✕")),
            held(PRESS_SHADOW, PRESS_MOVE),
            bareMarks(),
            // ── its type: the body face on every text, its display face where a text is set apart ──
            type(TEXT, BODY, null, null),
            type(HEADING, DISPLAY, "700", null),
            type(KICKER, DISPLAY, "700", "letter-spacing: 2px; text-transform: uppercase;"),
            type(LEDE, null, null, "font-style: italic;"),
            type(CAPTION, null, null, "font-style: italic;"),
            type(LABEL, null, "600", null),
            type(READOUT, DISPLAY, "700", "font-style: italic; font-variant-numeric: tabular-nums;"),
            type(WORDMARK, DISPLAY, null, "font-style: italic;"),
            // ── its separator ruled at two pixels ──
            separatorRule("2px"),
            // ── the veil behind a dialog: the page dimmed and softened; a way's colour eased under the hand ──
            veil("brightness(0.45) blur(2px)"),
            waysEase("color 140ms ease"),
            measure("8px", "18px", "8px", "56px", "1.3"),
            // ── its slider a hairline groove, the knob its grip alone, a fader's cap squared off ──
            sliderMeasure("220px", "24px", "4px", "16px", "14px", "28px"),
            sliderShape("999px", "1px", "50%", "1.5px", "3px"),
            markAlone(),
            currentKnob("2px solid -2px"),
            disabled(".45", "default"));
}
