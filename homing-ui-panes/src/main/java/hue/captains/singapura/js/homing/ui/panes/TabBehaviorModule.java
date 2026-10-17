package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;

import java.util.List;

/** What a tab does: the hand down on it, not on its cross, is its Press; a right-click asks its owner's opener for its menu. */
public record TabBehaviorModule() implements DomModule<TabBehaviorModule> {

    /** {@code new TabBehavior(el, tab)}. */
    public record TabBehavior() implements Exportable._Class<TabBehaviorModule> {}

    public static final TabBehaviorModule INSTANCE = new TabBehaviorModule();

    @Override
    public ImportsFor<TabBehaviorModule> imports() {
        return ImportsFor.<TabBehaviorModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .build();
    }

    @Override public ExportsOf<TabBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TabBehavior())); }
}
