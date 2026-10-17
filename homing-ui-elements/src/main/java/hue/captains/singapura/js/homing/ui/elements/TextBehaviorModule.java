package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What text does: nothing. Words are read, never pressed; a text leaf's behaviour listens for nothing. */
public record TextBehaviorModule() implements DomModule<TextBehaviorModule> {

    /** {@code new TextBehavior(el, text)}. */
    public record TextBehavior() implements Exportable._Class<TextBehaviorModule> {}

    public static final TextBehaviorModule INSTANCE = new TextBehaviorModule();

    @Override public ImportsFor<TextBehaviorModule> imports() { return ImportsFor.noImports(); }

    @Override public ExportsOf<TextBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TextBehavior())); }
}
