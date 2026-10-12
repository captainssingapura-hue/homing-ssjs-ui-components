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
import static hue.captains.singapura.js.homing.ui.styles.Say.paneRings;
import static hue.captains.singapura.js.homing.ui.styles.Say.siteChrome;
import static hue.captains.singapura.js.homing.ui.styles.Say.BUTTON;
import static hue.captains.singapura.js.homing.ui.styles.Say.CORNER;
import static hue.captains.singapura.js.homing.ui.styles.Say.EASE;
import static hue.captains.singapura.js.homing.ui.styles.Say.FACE;
import static hue.captains.singapura.js.homing.ui.styles.Say.BORDER;
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
import static hue.captains.singapura.js.homing.ui.styles.Say.thumbRule;
import static hue.captains.singapura.js.homing.ui.styles.Say.waysMarked;
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
import static hue.captains.singapura.js.homing.ui.styles.Say.tabMeasure;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabFrame;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabMotion;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardMeasure;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardFrame;
import static hue.captains.singapura.js.homing.ui.styles.Say.panel;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardMotion;

/**
 * Sketchy: drawn by hand - a wobbling outline, a marker's rule, a tilt under the hand. Ink casts no
 * shadow, so it says nothing of the shadow: the base's is none.
 */
public record Sketchy() implements DesignStyle, Derives<Base> {

    /** Its faces: the body's, and the display's for what is set apart. */
    private static final String BODY = "\"Neucha\", \"Segoe Print\", \"Bradley Hand\", \"Comic Sans MS\", cursive";
    private static final String DISPLAY = "\"Cabin Sketch\", \"Segoe Print\", \"Bradley Hand\", \"Chalkboard SE\", cursive";

    public static final Sketchy INSTANCE = new Sketchy();

    @Override public String group() { return "Expressive"; }

    @Override public String inspiration() { return "Drawn by hand in marker on paper — 2px lines, corners that wobble, nothing casts a shadow."; }

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    /** The words it draws otherwise than Base: drawn by hand, in pen. */
    @Override public List<Writing> writings() { return WRITINGS; }

    private static final List<Writing> WRITINGS = List.of(
            new Writing(HouseWords.Check.INSTANCE, "✔"),
            new Writing(HouseWords.Disclose.INSTANCE, "➤"),
            new Writing(HouseWords.Close.INSTANCE, "✖"),
            new Writing(HouseWords.Pin.INSTANCE, "⚲"),
            new Writing(HouseWords.Settings.INSTANCE, "⚒"),
            new Writing(HouseWords.Grip.INSTANCE, "⁘"));

    /** The press: pushed down, and tipped the other way. Ink casts no shadow. */
    private static final String PRESS_MOVE = "transform: translateY(1px) rotate(0.4deg);";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 255px 25px 225px 25px / 25px 225px 25px 255px;"),
                    rest(BUTTON, BORDER, "border-width: 2px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Cabin Sketch\", \"Segoe Print\", \"Bradley Hand\", \"Chalkboard SE\", cursive;"),
                    rest(BUTTON, EASE, "transition: transform 120ms ease-out, background-color 120ms ease-out, border-color 120ms ease-out;"),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-1px) rotate(-0.6deg);", State.ACTIVE, PRESS_MOVE)),
                    rings("2px dashed -3px", "2px dashed 5px", "2px dotted 3px", "3px solid 3px")),
            held(null, PRESS_MOVE),
            bareMarks(),
            // ── its type: the body face on every text, its display face where a text is set apart ──
            type(TEXT, BODY, null, null),
            type(HEADING, DISPLAY, "700", null),
            pageName("700", null, null, null),   // a page's own name, as the old Display was set
            type(KICKER, DISPLAY, "700", "letter-spacing: 0.06em; text-transform: uppercase;"),
            type(LABEL, null, "700", null),
            type(READOUT, DISPLAY, "700", "font-variant-numeric: tabular-nums;"),
            type(WORDMARK, DISPLAY, null, null),
            // ── its separator and a pane's thumbnail ruled at two pixels, as its pen draws ──
            separatorRule("2px"),
            thumbRule("2px"),
            // ── no veil behind a dialog: the page stays as it is drawn; a way underlined, wavily, when pointed at ──
            waysMarked("text-decoration-line: underline; text-decoration-style: wavy; text-underline-offset: 3px;"),
            measure("8px", "16px", "8px", "56px", "1.35"),
            // ── its slider drawn by hand, groove and knob alike, the marks in pen ──
            sliderMeasure("220px", "26px", "5px", "18px", "15px", "30px"),
            sliderShape("6px 4px 5px 7px / 4px 6px 7px 5px", "2px", "50% 45% 55% 50% / 45% 55% 50% 50%", "2px", "5px 3px 4px 6px / 3px 5px 6px 4px"),
            sliderMarks("2px", "14px", "2px", "8px"),
            currentKnob("2px dashed -3px"),
            // ── its menu drawn by hand, casting nothing ──
            menuFrame("8px 6px 7px 9px / 6px 8px 9px 7px", "2px", "8px", "2px", "200px"),
            menuRow("6px 4px 5px 7px / 4px 6px 7px 5px", "7px", "12px"),
            // ── its tabs drawn by hand, the chosen one ruled heavier and askew, casting nothing ──
            tabMeasure("160px", "5", "12px", "8px"),
            tabFrame("8px 6px 0 0 / 6px 8px 0 0", "2px", "3px", "2px"),
            tabMotion("transform 120ms ease-out, translate 120ms ease-out, background-color 120ms ease-out, border-color 120ms ease-out", null, "rotate(-0.4deg)", "translateY(-2px) rotate(-1deg)"),
            // ── its cards drawn by hand, tilted under the hand, casting nothing ──
            cardMeasure("280px", "16px", "18px", "8px"),
            cardFrame("25px 25px 55px 5px / 5px 55px 25px 25px", "2px"),
            cardMotion("transform 120ms ease-out, background-color 120ms ease-out, border-color 120ms ease-out", "translateY(-1px) rotate(-0.6deg)", "translateY(1px) rotate(0.4deg)"),
            disabled(".5", "not-allowed"),
            // ── its page: the face everything on it is written in, and the site's bar ──
            // ── its floating pane - a dialog's window is one: its shadows, its lift in the hand, its title bar ──
            // ── its panes ringed while the keys are on them, as its old design marked them ──
            List.of(paneRings("3px solid", "2px dashed", "2px dotted")),
            pane("none", "none", "none", "none", "translateY(-1px) rotate(-0.6deg)", "translateY(-2px) rotate(-1deg)", DISPLAY, null, null, "2px"),
            // ── its panel: the head underlined by hand, gone over twice; no depth - ink casts no shadow here ──
            panel("1.35", "10px", "16px", "10px", "3px double", "14px", "16px", "10px", "0px", "none", "none"),
            siteChrome(BODY, "2px", null));
}
