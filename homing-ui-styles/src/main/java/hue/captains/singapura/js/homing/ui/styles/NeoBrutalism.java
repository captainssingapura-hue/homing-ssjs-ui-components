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
import static hue.captains.singapura.js.homing.ui.styles.Say.BORDER;
import static hue.captains.singapura.js.homing.ui.styles.Say.SHADOW;
import static hue.captains.singapura.js.homing.ui.styles.Say.TRANSFORM;
import static hue.captains.singapura.js.homing.ui.styles.Say.answers;
import static hue.captains.singapura.js.homing.ui.styles.Say.disabled;
import static hue.captains.singapura.js.homing.ui.styles.Say.measure;
import static hue.captains.singapura.js.homing.ui.styles.Say.rest;
import static hue.captains.singapura.js.homing.ui.styles.Say.rings;
import static hue.captains.singapura.js.homing.ui.styles.Say.when;

/** Neo-brutalism: square, a heavy rule, a hard offset shadow, and a press that drops into it. */
public record NeoBrutalism() implements DesignStyle, Derives<Base> {

    public static final NeoBrutalism INSTANCE = new NeoBrutalism();

    @Override public Base base() { return Base.INSTANCE; }

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    private static final List<Implementation<?>> ANSWERS = answers(
            List.of(rest(BUTTON, CORNER, "border-radius: 0;"),
                    rest(BUTTON, BORDER, "border-width: 3px; border-style: solid;"),
                    rest(BUTTON, FACE, "font-family: \"Arial Black\", \"Impact\", \"Helvetica Neue\", Arial, sans-serif;"),
                    rest(BUTTON, EASE, "transition: transform 70ms steps(2, end), box-shadow 70ms steps(2, end), background-color 70ms steps(2, end);"),
                    when(BUTTON, SHADOW, Map.of(
                            State.REST, "box-shadow: 4px 4px 0 var(--box-shadow-cast);",
                            State.HOVER, "box-shadow: 8px 8px 0 var(--box-shadow-cast);",
                            State.ACTIVE, "box-shadow: 0 0 0 var(--box-shadow-cast);")),
                    when(BUTTON, TRANSFORM, Map.of(State.HOVER, "transform: translate(-2px, -2px);", State.ACTIVE, "transform: translate(5px, 5px);")),
                    rings("3px solid -3px", "4px dashed 4px", "4px dotted 4px", "4px solid 4px")),
            measure("10px", "20px", "10px", "64px", "1.5"),
            disabled(".4", "not-allowed"));
}
