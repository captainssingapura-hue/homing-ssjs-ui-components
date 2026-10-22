package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What a floating pane does: the hand over it, and off it, told to the pane; and while its head is held, each move of the hand told to the pane - until it is let go. */
public record FloatingPaneBehaviorModule() implements DomModule<FloatingPaneBehaviorModule> {

    /** {@code new FloatingPaneBehavior(el, pane)}; {@code grab()}, {@code drop()}. */
    public record FloatingPaneBehavior() implements Exportable._Class<FloatingPaneBehaviorModule> {}

    public static final FloatingPaneBehaviorModule INSTANCE = new FloatingPaneBehaviorModule();

    @Override
    public ImportsFor<FloatingPaneBehaviorModule> imports() {
        return ImportsFor.<FloatingPaneBehaviorModule>builder()
                .build();
    }

    @Override public ExportsOf<FloatingPaneBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new FloatingPaneBehavior())); }
}
