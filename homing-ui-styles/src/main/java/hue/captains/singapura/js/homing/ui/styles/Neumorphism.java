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
import static hue.captains.singapura.js.homing.ui.styles.Say.disabled;
import static hue.captains.singapura.js.homing.ui.styles.Say.measure;
import static hue.captains.singapura.js.homing.ui.styles.Say.rest;
import static hue.captains.singapura.js.homing.ui.styles.Say.rings;
import static hue.captains.singapura.js.homing.ui.styles.Say.when;

/**
 * Neumorphism: moulded out of the page's own clay - no rule, its depth all in light and shade,
 * pressed in when pressed. It draws no line, so says nothing of the rule: the base's is none.
 */
public record Neumorphism() implements DesignStyle, Derives<Base> {

    public static final Neumorphism INSTANCE = new Neumorphism();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    private static final String DARK = "color-mix(in srgb, #FAFBFD 80%, black)";
    private static final String LIGHT = "color-mix(in srgb, #FAFBFD 55%, white)";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 12px;"),
                    rest(BUTTON, FACE, "font-family: \"Nunito\", \"Quicksand\", \"Varela Round\", \"Segoe UI\", system-ui, sans-serif;"),
                    rest(BUTTON, EASE, "transition: box-shadow 180ms ease, transform 180ms ease, background-color 180ms ease, color 120ms ease;"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: 5px 5px 10px " + DARK + ", -5px -5px 10px " + LIGHT + ";",
                            State.HOVER, "box-shadow: 7px 7px 14px " + DARK + ", -7px -7px 14px " + LIGHT + ";",
                            State.ACTIVE, "box-shadow: inset 4px 4px 8px " + DARK + ", inset -4px -4px 8px " + LIGHT + ";")),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-1px);")),
                    rings("2px solid -2px", "2px dotted 4px", "2px solid -3px", "3px solid -3px")),
            measure("10px", "20px", "8px", "64px", "1.3"),
            disabled(".5", "not-allowed"));
}
