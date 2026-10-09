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

/** Glassmorphism: a rounded pane with a lit top edge, floating on a wide soft shadow. */
public record Glassmorphism() implements DesignStyle, Derives<Base> {

    public static final Glassmorphism INSTANCE = new Glassmorphism();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    private static final String RIM = "inset 0 1px 0 color-mix(in srgb, white 60%, transparent)";

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 12px;"),
                    rest(BUTTON, RULE, "border-width: 1px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Inter\", \"Segoe UI Variable Display\", \"Segoe UI\", system-ui, sans-serif;"),
                    rest(BUTTON, EASE, "transition: box-shadow 220ms ease, transform 220ms ease, background-color 160ms ease, border-color 160ms ease;"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: 0 4px 16px color-mix(in srgb, #3B4A6B 10%, transparent), " + RIM + ";",
                            State.HOVER, "box-shadow: 0 12px 32px color-mix(in srgb, #3B4A6B 16%, transparent), " + RIM + ";",
                            State.ACTIVE, "box-shadow: 0 2px 8px color-mix(in srgb, #3B4A6B 10%, transparent), " + RIM + ";")),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-2px);", State.ACTIVE, "transform: translateY(0);")),
                    rings("2px solid -2px", "1px dashed 4px", "1px solid 0", "2px solid 0")),
            measure("10px", "22px", "8px", "64px", "1.3"),
            disabled(".45", "not-allowed"));
}
