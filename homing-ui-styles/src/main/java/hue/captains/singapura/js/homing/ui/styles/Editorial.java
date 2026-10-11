package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;
import java.util.Map;

import static hue.captains.singapura.js.homing.ui.styles.Say.pane;
import static hue.captains.singapura.js.homing.ui.styles.Say.siteChrome;
import static hue.captains.singapura.js.homing.ui.styles.Say.BUTTON;
import static hue.captains.singapura.js.homing.ui.styles.Say.CORNER;
import static hue.captains.singapura.js.homing.ui.styles.Say.EASE;
import static hue.captains.singapura.js.homing.ui.styles.Say.FACE;
import static hue.captains.singapura.js.homing.ui.styles.Say.BORDER;
import static hue.captains.singapura.js.homing.ui.styles.Say.SHADOW;
import static hue.captains.singapura.js.homing.ui.styles.Say.TRANSFORM;
import static hue.captains.singapura.js.homing.ui.styles.Say.answers;
import static hue.captains.singapura.js.homing.ui.styles.Say.bareMarks;
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
import static hue.captains.singapura.js.homing.ui.styles.Say.menuFrame;
import static hue.captains.singapura.js.homing.ui.styles.Say.menuRow;
import static hue.captains.singapura.js.homing.ui.styles.Say.menuShadow;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabMeasure;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabFrame;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabMotion;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabShadow;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardMeasure;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardFrame;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardMotion;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardShadow;

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
                    rings("2px solid -2px", "1px dashed 3px", "1px solid -2px", "2px solid -2px")),
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
            // ── its menu a hairline frame, softly lifted ──
            menuFrame("6px", "1px", "6px", "2px", "200px"),
            menuRow("4px", "7px", "12px"),
            menuShadow("0 10px 30px color-mix(in srgb, var(--box-shadow-cast) 45%, transparent), 0 2px 6px color-mix(in srgb, var(--box-shadow-cast) 30%, transparent)"),
            // ── its tabs cut square at the foot, softly lifted when chosen; its strip ruled along its foot ──
            tabMeasure("168px", "6", "12px", "8px"),
            tabFrame("4px 4px 0 0", "1.5px", null, "2px"),
            tabMotion("transform 160ms ease, translate 160ms ease, box-shadow 160ms ease, border-color 160ms ease, background-color 160ms ease", "translateY(-1px)", "translateY(-2px)", "translateY(-4px)"),
            tabShadow("0 4px 12px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent)", "0 8px 20px color-mix(in srgb, var(--box-shadow-cast) 18%, transparent)", "0 14px 30px color-mix(in srgb, var(--box-shadow-cast) 20%, transparent)"),
            // ── its cards hairlined, softly lifted under the hand ──
            cardMeasure("280px", "18px", "20px", "8px"),
            cardFrame("6px", "1px"),
            cardMotion("transform 160ms ease, box-shadow 160ms ease, border-color 160ms ease", "translateY(-2px)", "translateY(0)"),
            cardShadow("0 1px 3px color-mix(in srgb, var(--box-shadow-cast) 4%, transparent)", "0 6px 16px color-mix(in srgb, var(--box-shadow-cast) 12%, transparent)", "0 1px 2px color-mix(in srgb, var(--box-shadow-cast) 8%, transparent)"),
            disabled(".45", "default"),
            // ── its page: the face everything on it is written in, and the site's bar ──
            // ── its floating pane - a dialog's window is one: its shadows, its lift in the hand, its title bar ──
            pane("0 10px 30px color-mix(in srgb, var(--box-shadow-cast) 45%, transparent), 0 2px 6px color-mix(in srgb, var(--box-shadow-cast) 30%, transparent)", "0 10px 30px color-mix(in srgb, var(--box-shadow-cast) 45%, transparent), 0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 28%, transparent), 0 0 36px color-mix(in srgb, var(--box-shadow-glow) 30%, transparent)", "0 6px 16px color-mix(in srgb, var(--box-shadow-glow) 12%, transparent)", "0 14px 30px color-mix(in srgb, var(--box-shadow-glow) 20%, transparent)", "translateY(-2px)", "translateY(-4px)", DISPLAY, null, null, null),
            siteChrome(BODY, "2px", null));
}
