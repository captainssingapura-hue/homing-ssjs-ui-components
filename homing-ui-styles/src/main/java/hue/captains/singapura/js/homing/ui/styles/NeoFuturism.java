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

/** Neo-futurism: a hairline, a lit rim that glows under the hand, a long eased glide. */
public record NeoFuturism() implements DesignStyle, Derives<Base> {

    public static final NeoFuturism INSTANCE = new NeoFuturism();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 2px;"),
                    rest(BUTTON, RULE, "border-width: 1px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Bahnschrift\", \"Eurostile\", \"Avenir Next\", \"Futura\", \"Segoe UI\", sans-serif;"),
                    rest(BUTTON, EASE, "transition: transform 220ms cubic-bezier(0.2, 0.8, 0.2, 1), box-shadow 220ms cubic-bezier(0.2, 0.8, 0.2, 1),"
                            + " border-color 220ms ease, background-color 220ms ease, color 160ms ease;"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: 0 0 0 1px color-mix(in srgb, #1E2761 14%, transparent), 0 12px 32px color-mix(in srgb, #111936 10%, transparent);",
                            State.HOVER, "box-shadow: 0 0 0 1px color-mix(in srgb, #1E2761 60%, transparent), 0 0 28px color-mix(in srgb, #1E2761 30%, transparent),"
                                    + " 0 12px 32px color-mix(in srgb, #111936 10%, transparent);",
                            State.ACTIVE, "box-shadow: 0 0 0 1px color-mix(in srgb, #1E2761 60%, transparent), 0 0 10px color-mix(in srgb, #1E2761 25%, transparent);")),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translateY(-1px) scale(1.01);", State.ACTIVE, "transform: scale(0.985);")),
                    rings("1px solid -1px", "1px dashed 5px", "1px solid 3px", "1px solid 3px")),
            measure("7px", "16px", "8px", "56px", "1.25"),
            disabled(".4", "not-allowed"));
}
