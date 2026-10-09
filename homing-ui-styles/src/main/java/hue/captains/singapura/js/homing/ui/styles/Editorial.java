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

/** Editorial, the house's own: a serif voice, a fine rule, a soft lift under the hand. */
public record Editorial() implements DesignStyle, Derives<Base> {

    public static final Editorial INSTANCE = new Editorial();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 3px;"),
                    rest(BUTTON, RULE, "border-width: 1.5px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Georgia\", serif;"),
                    rest(BUTTON, EASE, "transition: transform 160ms ease, box-shadow 160ms ease, border-color 160ms ease;"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: 0 1px 3px color-mix(in srgb, #111936 4%, transparent);",
                            State.HOVER, "box-shadow: 0 6px 16px color-mix(in srgb, #111936 12%, transparent);",
                            State.ACTIVE, "box-shadow: 0 1px 2px color-mix(in srgb, #111936 8%, transparent);")),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-2px);", State.ACTIVE, "transform: translateY(0);")),
                    rings("2px solid -2px", "1px dashed 3px", "1px solid -2px", "2px solid -2px")),
            measure("8px", "18px", "8px", "56px", "1.3"),
            disabled(".45", "default"));
}
