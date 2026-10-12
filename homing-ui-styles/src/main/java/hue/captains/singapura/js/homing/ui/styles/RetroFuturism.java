package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.design.semantic.Writing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseWords;

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
import static hue.captains.singapura.js.homing.ui.styles.Say.pageName;
import static hue.captains.singapura.js.homing.ui.styles.Say.separatorRule;
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
import static hue.captains.singapura.js.homing.ui.styles.Say.panel;
import static hue.captains.singapura.js.homing.ui.styles.Say.prose;

/** Retro-futurism: a neon ring that flares under the hand, and motion in steps, as a screen of the period moved. */
public record RetroFuturism() implements DesignStyle, Derives<Base> {

    /** Its faces: the body's, and the display's for what is set apart. */
    private static final String BODY = "\"Rajdhani\", \"Bahnschrift\", \"Segoe UI\", system-ui, sans-serif";
    private static final String DISPLAY = "\"Orbitron\", \"Audiowide\", \"Bahnschrift\", \"Eurostile\", \"Impact\", sans-serif";

    public static final RetroFuturism INSTANCE = new RetroFuturism();

    @Override public String group() { return "Expressive"; }

    @Override public String inspiration() { return "A magenta sun over a cyan grid — everything outlined in neon and glowing."; }

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    /** The words it draws otherwise than Base: its own cross. */
    @Override public List<Writing> writings() { return WRITINGS; }

    private static final List<Writing> WRITINGS = List.of(
            new Writing(HouseWords.Close.INSTANCE, "✘"));

    private static final String RING = "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent)";

    /** The press: the neon dimmed, the button a step down. */
    private static final String PRESS_SHADOW = "box-shadow: " + RING + ", 0 0 6px color-mix(in srgb, var(--box-shadow-glow) 40%, transparent);";
    private static final String PRESS_MOVE = "transform: translateY(1px);";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 3px;"),
                    rest(BUTTON, BORDER, "border-width: 1px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Orbitron\", \"Audiowide\", \"Bahnschrift\", \"Eurostile\", \"Impact\", sans-serif;"),
                    rest(BUTTON, EASE, "transition: box-shadow 140ms ease-out, transform 140ms steps(3, end), background-color 120ms ease-out,"
                            + " border-color 120ms ease-out, color 120ms ease-out;"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: " + RING + ", 0 0 10px color-mix(in srgb, var(--box-shadow-glow) 25%, transparent);",
                            State.HOVER, "box-shadow: " + RING + ", 0 0 24px color-mix(in srgb, var(--box-shadow-glow) 55%, transparent);",
                            State.ACTIVE, PRESS_SHADOW)),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-2px);", State.ACTIVE, PRESS_MOVE)),
                    rings("1px solid -1px", "1px dashed 4px", "1px solid 2px", "2px solid 2px")),
            held(PRESS_SHADOW, PRESS_MOVE),
            bareMarks(),
            // ── its type: the body face on every text, its display face where a text is set apart ──
            type(TEXT, BODY, null, null),
            type(HEADING, DISPLAY, "700", null),
            pageName("800", "letter-spacing: 0.12em; text-transform: uppercase;", "40px", "1.15"),   // a page's own name, as the old Display was set
            type(KICKER, DISPLAY, "700", "letter-spacing: 0.24em; text-transform: uppercase;"),
            type(LEDE, null, null, "letter-spacing: 0.02em;"),
            type(CAPTION, null, null, "letter-spacing: 0.04em;"),
            type(LABEL, null, "700", null),
            type(READOUT, DISPLAY, "700", "letter-spacing: 0.06em; font-variant-numeric: tabular-nums;"),
            type(WORDMARK, DISPLAY, null, "letter-spacing: 0.2em; text-transform: uppercase;"),
            // ── its separator ruled at two pixels ──
            separatorRule("2px"),
            // ── the veil behind a dialog: a little blur, a little contrast ──
            veil("blur(4px) contrast(1.2)"),
            measure("8px", "18px", "8px", "56px", "1.3"),
            // ── its slider a ruled bar, the knob its mark alone ──
            sliderMeasure("220px", "26px", "6px", "18px", "14px", "28px"),
            sliderShape("3px", "2px", "3px", "1px", "2px"),
            sliderMarks("2px", "14px", "1px", "7px"),
            markAlone(),
            currentKnob("1px solid -1px"),
            // ── its menu glowing over the page ──
            menuFrame("3px", "1px", "6px", "2px", "200px"),
            menuRow("2px", "7px", "12px"),
            menuShadow("0 0 48px color-mix(in srgb, var(--box-shadow-glow) 45%, transparent), 0 24px 64px color-mix(in srgb, var(--box-shadow-cast) 60%, transparent)"),
            // ── its tabs glowing, stepping up in frames ──
            tabMeasure("168px", "5.5", "12px", "8px"),
            tabFrame("3px 3px 0 0", "1px", null, "2px"),
            tabMotion("box-shadow 140ms ease-out, transform 140ms steps(3, end), translate 140ms steps(3, end), background-color 120ms ease-out, border-color 120ms ease-out, color 120ms ease-out", null, "translateY(-1px)", "translateY(-3px)"),
            tabShadow("0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent), 0 0 10px color-mix(in srgb, var(--box-shadow-glow) 30%, transparent)", "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent), 0 0 28px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent)", "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent), 0 0 36px color-mix(in srgb, var(--box-shadow-glow) 80%, transparent)"),
            // ── its cards glowing, more under the hand ──
            cardMeasure("280px", "18px", "20px", "8px"),
            cardFrame("4px", "1px"),
            cardMotion("box-shadow 140ms ease-out, transform 140ms steps(3, end), background-color 120ms ease-out, border-color 120ms ease-out, color 120ms ease-out", "translateY(-2px)", "translateY(1px)"),
            cardShadow("0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent), 0 0 10px color-mix(in srgb, var(--box-shadow-glow) 25%, transparent)", "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent), 0 0 24px color-mix(in srgb, var(--box-shadow-glow) 55%, transparent)", "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent), 0 0 6px color-mix(in srgb, var(--box-shadow-glow) 40%, transparent)"),
            disabled(".4", "not-allowed"),
            // ── its page: the face everything on it is written in, and the site's bar ──
            // ── its floating pane - a dialog's window is one: its shadows, its lift in the hand, its title bar ──
            pane("0 0 48px color-mix(in srgb, var(--box-shadow-glow) 45%, transparent), 0 24px 64px color-mix(in srgb, var(--box-shadow-cast) 60%, transparent)", "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent), 0 0 40px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent), 0 24px 64px color-mix(in srgb, var(--box-shadow-cast) 30%, transparent)", "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent), 0 0 24px color-mix(in srgb, var(--box-shadow-glow) 55%, transparent)", "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 70%, transparent), 0 0 36px color-mix(in srgb, var(--box-shadow-glow) 80%, transparent)", "translateY(-2px)", "translateY(-3px)", DISPLAY, null, null, null),
            // ── its panel: a console's header, the rule under it lit by the palette; the neon burns out over the void, or inward into it ──
            panel("1.3", "8px", "14px", "10px", "1px solid", "12px", "16px", "10px", "0px", "0 0 26px color-mix(in srgb, var(--box-shadow-glow) 45%, transparent)", "inset 0 0 18px color-mix(in srgb, var(--box-shadow-cast) 70%, transparent)"),
            // ── its prose, as its old Prose word ruled it ──
            prose("2px", "1px", "0", "2px"),
            siteChrome(BODY, "2px", null));
}
