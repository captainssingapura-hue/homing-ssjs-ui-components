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
import static hue.captains.singapura.js.homing.ui.styles.Say.thumbRule;
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

/** Neo-brutalism: square, a heavy rule, a hard offset shadow, and a press that drops into it. */
public record NeoBrutalism() implements DesignStyle, Derives<Base> {

    /** Its faces: the body's, and the display's for what is set apart. */
    private static final String BODY = "\"Helvetica Neue\", Arial, sans-serif";
    private static final String DISPLAY = "\"Arial Black\", \"Impact\", \"Helvetica Neue\", Arial, sans-serif";

    public static final NeoBrutalism INSTANCE = new NeoBrutalism();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    /** The press: pushed into its own shadow. */
    private static final String PRESS_SHADOW = "box-shadow: 0 0 0 var(--box-shadow-cast);";
    private static final String PRESS_MOVE = "transform: translate(5px, 5px);";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 0;"),
                    rest(BUTTON, BORDER, "border-width: 3px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Arial Black\", \"Impact\", \"Helvetica Neue\", Arial, sans-serif;"),
                    rest(BUTTON, EASE, "transition: transform 70ms steps(2, end), box-shadow 70ms steps(2, end), background-color 70ms steps(2, end);"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: 4px 4px 0 var(--box-shadow-cast);",
                            State.HOVER, "box-shadow: 8px 8px 0 var(--box-shadow-cast);",
                            State.ACTIVE, PRESS_SHADOW)),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translate(-2px, -2px);", State.ACTIVE, PRESS_MOVE)),
                    rings("3px solid -3px", "4px dashed 4px", "4px dotted 4px", "4px solid 4px"),
                    closeMark("╳")),
            held(PRESS_SHADOW, PRESS_MOVE),
            bareMarks(),
            // ── its type: the body face on every text, its display face where a text is set apart ──
            type(TEXT, BODY, null, null),
            type(HEADING, DISPLAY, "900", null),
            type(KICKER, DISPLAY, "900", "letter-spacing: 0.04em; text-transform: uppercase;"),
            type(LABEL, null, "700", null),
            type(READOUT, DISPLAY, "900", "font-variant-numeric: tabular-nums;"),
            type(WORDMARK, DISPLAY, null, "letter-spacing: -0.02em; text-transform: uppercase;"),
            // ── its separator ruled hard, at four pixels ──
            separatorRule("4px"),
            // ── a pane's thumbnail ruled hard too, at three ──
            thumbRule("3px"),
            // ── the veil behind a dialog: the page drained of colour, hard ──
            veil("grayscale(1) contrast(1.4)"),
            measure("10px", "20px", "10px", "64px", "1.5"),
            // ── its slider a thick ruled bar, the knob a square block, the marks heavy ──
            sliderMeasure("240px", "28px", "10px", "20px", "16px", "32px"),
            sliderShape("0", "3px", "0", "3px", "0"),
            sliderMarks("3px", "18px", "2px", "8px"),
            currentKnob("3px solid -3px"),
            disabled(".4", "not-allowed"));
}
