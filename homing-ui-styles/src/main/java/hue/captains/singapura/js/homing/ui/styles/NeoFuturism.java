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

/** Neo-futurism: a hairline, a lit rim that glows under the hand, a long eased glide. */
public record NeoFuturism() implements DesignStyle, Derives<Base> {

    /** Its faces: the body's, and the display's for what is set apart. */
    private static final String BODY = "\"Segoe UI Variable Text\", \"Segoe UI\", \"Inter\", system-ui, sans-serif";
    private static final String DISPLAY = "\"Bahnschrift\", \"Eurostile\", \"Avenir Next\", \"Futura\", \"Segoe UI\", sans-serif";

    public static final NeoFuturism INSTANCE = new NeoFuturism();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    /** The words it draws otherwise than Base: its own cross. */
    @Override public List<Writing> writings() { return WRITINGS; }

    private static final List<Writing> WRITINGS = List.of(
            new Writing(HouseWords.Close.INSTANCE, "⨯"));

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
                    rings("1px solid -1px", "1px dashed 5px", "1px solid 3px", "1px solid 3px")),
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
            // ── its slider a fine wire, the knob its mark alone, the marks fine ──
            sliderMeasure("240px", "22px", "2px", "18px", "12px", "26px"),
            sliderShape("999px", "1px", "0", "1px", "2px"),
            sliderMarks("1px", "10px", "1px", "6px"),
            markAlone(),
            currentKnob("1px solid -1px"),
            // ── its menu a fine panel, deep and edged with a glow ──
            menuFrame("2px", "1px", "6px", "1px", "210px"),
            menuRow("2px", "7px", "14px"),
            menuShadow("0 24px 64px color-mix(in srgb, var(--box-shadow-cast) 35%, transparent), 0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 30%, transparent)"),
            // ── its tabs fine-edged, glowing when chosen; its strip a hairline along its foot ──
            tabMeasure("184px", "6.5", "14px", "8px"),
            tabFrame("2px 2px 0 0", "1px", null, "1px"),
            tabMotion("transform 220ms cubic-bezier(0.2, 0.8, 0.2, 1), translate 220ms cubic-bezier(0.2, 0.8, 0.2, 1), box-shadow 220ms cubic-bezier(0.2, 0.8, 0.2, 1), border-color 220ms ease, background-color 220ms ease, color 160ms ease", "translateY(-1px)", "translateY(-1px)", "translateY(-2px) scale(1.015)"),
            tabShadow("0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 45%, transparent), 0 0 16px color-mix(in srgb, var(--box-shadow-glow) 25%, transparent)", "0 0 0 1px var(--box-shadow-glow), 0 0 36px color-mix(in srgb, var(--box-shadow-glow) 45%, transparent), 0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent)", "0 0 0 1px var(--box-shadow-glow), 0 0 40px color-mix(in srgb, var(--box-shadow-glow) 55%, transparent), 0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent)"),
            // ── its cards fine panels, glowing under the hand ──
            cardMeasure("320px", "16px", "18px", "8px"),
            cardFrame("2px", "1px"),
            cardMotion("transform 220ms cubic-bezier(0.2, 0.8, 0.2, 1), box-shadow 220ms cubic-bezier(0.2, 0.8, 0.2, 1), border-color 220ms ease, background-color 220ms ease, color 160ms ease", "translateY(-1px) scale(1.01)", "scale(0.985)"),
            cardShadow("0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 14%, transparent), 0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent)", "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 60%, transparent), 0 0 28px color-mix(in srgb, var(--box-shadow-glow) 30%, transparent), 0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent)", "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 60%, transparent), 0 0 10px color-mix(in srgb, var(--box-shadow-glow) 25%, transparent)"),
            disabled(".4", "not-allowed"),
            // ── its page: the face everything on it is written in, and the site's bar ──
            // ── its floating pane - a dialog's window is one: its shadows, its lift in the hand, its title bar ──
            pane("0 24px 64px color-mix(in srgb, var(--box-shadow-cast) 35%, transparent), 0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 30%, transparent)", "0 24px 64px color-mix(in srgb, var(--box-shadow-cast) 35%, transparent), 0 0 0 1px var(--box-shadow-glow), 0 0 40px color-mix(in srgb, var(--box-shadow-glow) 35%, transparent)", "0 0 0 1px color-mix(in srgb, var(--box-shadow-glow) 60%, transparent), 0 0 28px color-mix(in srgb, var(--box-shadow-glow) 30%, transparent), 0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent)", "0 0 0 1px var(--box-shadow-glow), 0 0 40px color-mix(in srgb, var(--box-shadow-glow) 55%, transparent), 0 12px 32px color-mix(in srgb, var(--box-shadow-cast) 10%, transparent)", "translateY(-1px) scale(1.01)", "translateY(-2px) scale(1.015)", DISPLAY, "padding-block: 4px; padding-inline: 10px;", null, null),
            siteChrome(BODY, null, null));
}
