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
import static hue.captains.singapura.js.homing.ui.styles.Say.waysMarked;
import static hue.captains.singapura.js.homing.ui.styles.Say.TEXT;
import static hue.captains.singapura.js.homing.ui.styles.Say.HEADING;
import static hue.captains.singapura.js.homing.ui.styles.Say.KICKER;
import static hue.captains.singapura.js.homing.ui.styles.Say.CAPTION;
import static hue.captains.singapura.js.homing.ui.styles.Say.LEDE;
import static hue.captains.singapura.js.homing.ui.styles.Say.LABEL;
import static hue.captains.singapura.js.homing.ui.styles.Say.READOUT;
import static hue.captains.singapura.js.homing.ui.styles.Say.WORDMARK;

/**
 * Sketchy: drawn by hand - a wobbling outline, a marker's rule, a tilt under the hand. Ink casts no
 * shadow, so it says nothing of the shadow: the base's is none.
 */
public record Sketchy() implements DesignStyle, Derives<Base> {

    /** Its faces: the body's, and the display's for what is set apart. */
    private static final String BODY = "\"Neucha\", \"Segoe Print\", \"Bradley Hand\", \"Comic Sans MS\", cursive";
    private static final String DISPLAY = "\"Cabin Sketch\", \"Segoe Print\", \"Bradley Hand\", \"Chalkboard SE\", cursive";

    public static final Sketchy INSTANCE = new Sketchy();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    /** The press: pushed down, and tipped the other way. Ink casts no shadow. */
    private static final String PRESS_MOVE = "transform: translateY(1px) rotate(0.4deg);";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 255px 25px 225px 25px / 25px 225px 25px 255px;"),
                    rest(BUTTON, BORDER, "border-width: 2px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Cabin Sketch\", \"Segoe Print\", \"Bradley Hand\", \"Chalkboard SE\", cursive;"),
                    rest(BUTTON, EASE, "transition: transform 120ms ease-out, background-color 120ms ease-out, border-color 120ms ease-out;"),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-1px) rotate(-0.6deg);", State.ACTIVE, PRESS_MOVE)),
                    rings("2px dashed -3px", "2px dashed 5px", "2px dotted 3px", "3px solid 3px"),
                    closeMark("✖")),
            held(null, PRESS_MOVE),
            bareMarks(),
            // ── its type: the body face on every text, its display face where a text is set apart ──
            type(TEXT, BODY, null, null),
            type(HEADING, DISPLAY, "700", null),
            type(KICKER, DISPLAY, "700", "letter-spacing: 0.06em; text-transform: uppercase;"),
            type(LABEL, null, "700", null),
            type(READOUT, DISPLAY, "700", "font-variant-numeric: tabular-nums;"),
            type(WORDMARK, DISPLAY, null, null),
            // ── its separator ruled at two pixels, as its pen draws ──
            separatorRule("2px"),
            // ── no veil behind a dialog: the page stays as it is drawn; a way underlined, wavily, when pointed at ──
            waysMarked("text-decoration-line: underline; text-decoration-style: wavy; text-underline-offset: 3px;"),
            measure("8px", "16px", "8px", "56px", "1.35"),
            disabled(".5", "not-allowed"));
}
