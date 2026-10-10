package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What a mark does: nothing. A mark is seen, never pressed; a mark leaf's behaviour listens for nothing. */
public record MarkBehaviorModule() implements DomModule<MarkBehaviorModule> {

    /** {@code new MarkBehavior(el, mark)}. */
    public record MarkBehavior() implements Exportable._Class<MarkBehaviorModule> {}

    public static final MarkBehaviorModule INSTANCE = new MarkBehaviorModule();

    @Override public ImportsFor<MarkBehaviorModule> imports() { return ImportsFor.noImports(); }

    @Override public ExportsOf<MarkBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new MarkBehavior())); }
}
