package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/**
 * What the browser's own inputs do: each rides on its native element - the element keeps the value and the tick and
 * fires its events - and turns what the element does into a change of its component's states (RFC 0069, amended).
 */
public record InputBehaviorsModule() implements DomModule<InputBehaviorsModule> {

    /** {@code new EditBehavior(el, box)}: a word typed, the text box's draft; the user done, its committed text. */
    public record EditBehavior() implements Exportable._Class<InputBehaviorsModule> {}

    /** {@code new SlideBehavior(el, range)}: the knob moved, the range's degree; the hand taking it and letting go, its dragging. */
    public record SlideBehavior() implements Exportable._Class<InputBehaviorsModule> {}

    /** {@code new PickBehavior(el, select)}: a pick made, the select's chosen option. */
    public record PickBehavior() implements Exportable._Class<InputBehaviorsModule> {}

    /** {@code new CheckBehavior(el, box)}: a tick turned by the user, its Checked state's change. */
    public record CheckBehavior() implements Exportable._Class<InputBehaviorsModule> {}

    /** {@code new TurnBehavior(el, sw)}: a press, the switch turned - its groove with it. */
    public record TurnBehavior() implements Exportable._Class<InputBehaviorsModule> {}

    public static final InputBehaviorsModule INSTANCE = new InputBehaviorsModule();

    @Override public ImportsFor<InputBehaviorsModule> imports() {
        return ImportsFor.noImports();
    }

    @Override public ExportsOf<InputBehaviorsModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new EditBehavior(), new SlideBehavior(), new PickBehavior(), new CheckBehavior(), new TurnBehavior()));
    }
}
