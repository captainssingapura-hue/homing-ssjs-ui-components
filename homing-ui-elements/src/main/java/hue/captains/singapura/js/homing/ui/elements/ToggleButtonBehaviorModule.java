package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/**
 * What a toggle button does: a press, while it is enabled, turns it on or off through the
 * toggle's own setter, and says which. It keeps no state of its own and imports no style - it never
 * touches the look.
 */
public record ToggleButtonBehaviorModule() implements DomModule<ToggleButtonBehaviorModule> {

    /** {@code new ToggleButtonBehavior(el, toggle, { onToggle })}. */
    public record ToggleButtonBehavior() implements Exportable._Class<ToggleButtonBehaviorModule> {}

    public static final ToggleButtonBehaviorModule INSTANCE = new ToggleButtonBehaviorModule();

    @Override public ImportsFor<ToggleButtonBehaviorModule> imports() { return ImportsFor.noImports(); }

    @Override public ExportsOf<ToggleButtonBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ToggleButtonBehavior())); }
}
