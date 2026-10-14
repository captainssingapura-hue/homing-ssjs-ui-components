package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;

import java.util.List;

/**
 * What the browser's own inputs do: each rides on its native element - the element keeps the value and the tick and
 * fires the events - and dresses what the element does as its component's.
 */
public record InputBehaviorsModule() implements DomModule<InputBehaviorsModule> {

    /** {@code new EditBehavior(el, input)}: a value changing, the user still at it, its Input; the user done, its Commit. */
    public record EditBehavior() implements Exportable._Class<InputBehaviorsModule> {}

    /** {@code new PickBehavior(el, select)}: a pick made, its Commit. */
    public record PickBehavior() implements Exportable._Class<InputBehaviorsModule> {}

    /** {@code new CheckBehavior(el, box)}: a tick turned by the user, its Checked state's change, then its Commit. */
    public record CheckBehavior() implements Exportable._Class<InputBehaviorsModule> {}

    public static final InputBehaviorsModule INSTANCE = new InputBehaviorsModule();

    @Override public ImportsFor<InputBehaviorsModule> imports() {
        return ImportsFor.<InputBehaviorsModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .build();
    }

    @Override public ExportsOf<InputBehaviorsModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new EditBehavior(), new PickBehavior(), new CheckBehavior()));
    }
}
