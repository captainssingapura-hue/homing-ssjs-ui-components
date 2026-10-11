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
import static hue.captains.singapura.js.homing.ui.styles.Say.menuFrame;
import static hue.captains.singapura.js.homing.ui.styles.Say.menuRow;
import static hue.captains.singapura.js.homing.ui.styles.Say.menuShadow;
import static hue.captains.singapura.js.homing.ui.styles.Say.menuBackdrop;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabMeasure;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabFrame;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabMotion;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabShadow;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardMeasure;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardFrame;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardMotion;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardShadow;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardBackdrop;

/** Glassmorphism: a rounded pane with a lit top edge, floating on a wide soft shadow. */
public record Glassmorphism() implements DesignStyle, Derives<Base> {

    /** Its faces: the body's, and the display's for what is set apart. */
    private static final String BODY = "\"Inter\", \"Segoe UI Variable Text\", \"Segoe UI\", system-ui, sans-serif";
    private static final String DISPLAY = "\"Inter\", \"Segoe UI Variable Display\", \"Segoe UI\", system-ui, sans-serif";

    public static final Glassmorphism INSTANCE = new Glassmorphism();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    private static final String RIM = "inset 0 1px 0 color-mix(in srgb, var(--box-shadow-lit) 60%, transparent)";

    /** The press: settled close to the page, the rim still lit. */
    private static final String PRESS_SHADOW = "box-shadow: 0 2px 8px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent), " + RIM + ";";
    private static final String PRESS_MOVE = "transform: translateY(0);";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 12px;"),
                    rest(BUTTON, BORDER, "border-width: 1px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Inter\", \"Segoe UI Variable Display\", \"Segoe UI\", system-ui, sans-serif;"),
                    rest(BUTTON, EASE, "transition: box-shadow 220ms ease, transform 220ms ease, background-color 160ms ease, border-color 160ms ease;"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: 0 4px 16px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent), " + RIM + ";",
                            State.HOVER, "box-shadow: 0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 16%, transparent), " + RIM + ";",
                            State.ACTIVE, PRESS_SHADOW)),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-2px);", State.ACTIVE, PRESS_MOVE)),
                    rings("2px solid -2px", "1px dashed 4px", "1px solid 0", "2px solid 0"),
                    closeMark("×")),
            held(PRESS_SHADOW, PRESS_MOVE),
            bareMarks(),
            // ── its type: the body face on every text, its display face where a text is set apart ──
            type(TEXT, BODY, null, null),
            type(HEADING, DISPLAY, "600", null),
            type(KICKER, DISPLAY, "600", "letter-spacing: 0.14em; text-transform: uppercase;"),
            type(LABEL, null, "600", null),
            type(READOUT, DISPLAY, "600", "letter-spacing: -0.01em; font-variant-numeric: tabular-nums;"),
            type(WORDMARK, DISPLAY, null, "letter-spacing: -0.01em;"),
            // ── the veil behind a dialog: frosted ──
            veil("blur(10px) saturate(1.2) brightness(0.9)"),
            measure("10px", "22px", "8px", "64px", "1.3"),
            // ── its slider a rounded pane of glass, the knob a disc ──
            sliderMeasure("220px", "26px", "6px", "18px", "14px", "28px"),
            sliderShape("999px", "1px", "50%", "1px", "4px"),
            currentKnob("2px solid -2px"),
            // ── its menu a pane of frosted glass ──
            menuFrame("12px", "1px", "8px", "2px", "210px"),
            menuRow("8px", "8px", "14px"),
            menuShadow("0 24px 80px color-mix(in srgb, var(--box-shadow-cast) 35%, transparent)"),
            menuBackdrop("blur(16px) saturate(1.6)"),
            // ── its tabs panes of glass, lit along their top edge when chosen ──
            tabMeasure("176px", "5.5", "14px", "8px"),
            tabFrame("10px 10px 0 0", "1px", null, "1px"),
            tabMotion("box-shadow 220ms ease, transform 220ms ease, translate 220ms ease, background-color 160ms ease, border-color 160ms ease", "translateY(-1px)", "translateY(-2px)", "translateY(-4px)"),
            tabShadow("0 4px 14px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent)", "0 8px 24px color-mix(in srgb, var(--box-shadow-cast) 16%, transparent), inset 0 1px 0 var(--box-shadow-lit)", "0 16px 40px color-mix(in srgb, var(--box-shadow-cast) 22%, transparent), inset 0 1px 0 var(--box-shadow-lit)"),
            // ── its cards panes of frosted glass, lit along their top edge ──
            cardMeasure("300px", "20px", "22px", "8px"),
            cardFrame("18px", "1px"),
            cardMotion("box-shadow 220ms ease, transform 220ms ease, background-color 160ms ease, border-color 160ms ease", "translateY(-2px)", "translateY(0)"),
            cardShadow("0 4px 16px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent), inset 0 1px 0 var(--box-shadow-lit)", "0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 16%, transparent), inset 0 1px 0 var(--box-shadow-lit)", "0 2px 8px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent), inset 0 1px 0 var(--box-shadow-lit)"),
            cardBackdrop("blur(16px) saturate(1.6)"),
            disabled(".45", "not-allowed"),
            // ── its page: the face everything on it is written in, and the site's bar ──
            // ── its floating pane - a dialog's window is one: its shadows, its lift in the hand, its title bar ──
            pane("0 24px 80px color-mix(in srgb, var(--box-shadow-cast) 35%, transparent)", "0 24px 80px color-mix(in srgb, var(--box-shadow-cast) 30%, transparent), 0 0 0 3px color-mix(in srgb, var(--box-shadow-glow) 40%, transparent)", "0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 16%, transparent), inset 0 1px 0 var(--box-shadow-lit)", "0 16px 40px color-mix(in srgb, var(--box-shadow-cast) 22%, transparent), inset 0 1px 0 var(--box-shadow-lit)", "translateY(-2px)", "translateY(-4px)", DISPLAY, "padding-block: 6px; padding-inline: 14px;", null, null),
            siteChrome(BODY, null, "blur(20px) saturate(1.4)"));
}
