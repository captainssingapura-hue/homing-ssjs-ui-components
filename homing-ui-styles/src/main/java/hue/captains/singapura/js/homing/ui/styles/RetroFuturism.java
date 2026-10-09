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
import static hue.captains.singapura.js.homing.ui.styles.Say.RULE;
import static hue.captains.singapura.js.homing.ui.styles.Say.SHADOW;
import static hue.captains.singapura.js.homing.ui.styles.Say.TRANSFORM;
import static hue.captains.singapura.js.homing.ui.styles.Say.answers;
import static hue.captains.singapura.js.homing.ui.styles.Say.disabled;
import static hue.captains.singapura.js.homing.ui.styles.Say.measure;
import static hue.captains.singapura.js.homing.ui.styles.Say.rest;
import static hue.captains.singapura.js.homing.ui.styles.Say.rings;
import static hue.captains.singapura.js.homing.ui.styles.Say.when;

/** Retro-futurism: a neon ring that flares under the hand, and motion in steps, as a screen of the period moved. */
public record RetroFuturism() implements DesignStyle, Derives<Base> {

    public static final RetroFuturism INSTANCE = new RetroFuturism();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    private static final String RING = "0 0 0 1px color-mix(in srgb, #1E2761 70%, transparent)";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 3px;"),
                    rest(BUTTON, RULE, "border-width: 1px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Orbitron\", \"Audiowide\", \"Bahnschrift\", \"Eurostile\", \"Impact\", sans-serif;"),
                    rest(BUTTON, EASE, "transition: box-shadow 140ms ease-out, transform 140ms steps(3, end), background-color 120ms ease-out,"
                            + " border-color 120ms ease-out, color 120ms ease-out;"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: " + RING + ", 0 0 10px color-mix(in srgb, #1E2761 25%, transparent);",
                            State.HOVER, "box-shadow: " + RING + ", 0 0 24px color-mix(in srgb, #1E2761 55%, transparent);",
                            State.ACTIVE, "box-shadow: " + RING + ", 0 0 6px color-mix(in srgb, #1E2761 40%, transparent);")),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-2px);", State.ACTIVE, "transform: translateY(1px);")),
                    rings("1px solid -1px", "1px dashed 4px", "1px solid 2px", "2px solid 2px")),
            measure("8px", "18px", "8px", "56px", "1.3"),
            disabled(".4", "not-allowed"));
}
