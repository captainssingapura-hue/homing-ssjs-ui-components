package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What a title bar does: the hand down on it, but on its close, is its press, and up again, wherever it is by then, its release - each told to the head. */
public record TitleBarBehaviorModule() implements DomModule<TitleBarBehaviorModule> {

    /** {@code new TitleBarBehavior(el, head)}. */
    public record TitleBarBehavior() implements Exportable._Class<TitleBarBehaviorModule> {}

    public static final TitleBarBehaviorModule INSTANCE = new TitleBarBehaviorModule();

    @Override
    public ImportsFor<TitleBarBehaviorModule> imports() {
        return ImportsFor.<TitleBarBehaviorModule>builder()
                .build();
    }

    @Override public ExportsOf<TitleBarBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TitleBarBehavior())); }
}
