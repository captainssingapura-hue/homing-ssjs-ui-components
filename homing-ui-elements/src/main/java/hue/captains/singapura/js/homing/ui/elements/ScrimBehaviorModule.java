package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What a veil does: the hand coming down on it is its press, dressed from the DOM's and fired to its handler. */
public record ScrimBehaviorModule() implements DomModule<ScrimBehaviorModule> {

    /** {@code new ScrimBehavior(el, scrim)}. */
    public record ScrimBehavior() implements Exportable._Class<ScrimBehaviorModule> {}

    public static final ScrimBehaviorModule INSTANCE = new ScrimBehaviorModule();

    @Override
    public ImportsFor<ScrimBehaviorModule> imports() {
        return ImportsFor.noImports();
    }

    @Override public ExportsOf<ScrimBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ScrimBehavior())); }
}
