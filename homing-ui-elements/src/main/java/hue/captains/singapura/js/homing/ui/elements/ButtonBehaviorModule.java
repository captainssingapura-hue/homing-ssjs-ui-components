package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/**
 * What a button does: a press acts while it is enabled. Shared by the button leaves - the toggle's press its own; it reads
 * the state through the button, keeps none of its own, and imports no style - it never touches the
 * look.
 */
public record ButtonBehaviorModule() implements DomModule<ButtonBehaviorModule> {

    /** {@code new ButtonBehavior(el, button, { onClick })}. */
    public record ButtonBehavior() implements Exportable._Class<ButtonBehaviorModule> {}

    public static final ButtonBehaviorModule INSTANCE = new ButtonBehaviorModule();

    @Override public ImportsFor<ButtonBehaviorModule> imports() { return ImportsFor.noImports(); }

    @Override public ExportsOf<ButtonBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ButtonBehavior())); }
}
