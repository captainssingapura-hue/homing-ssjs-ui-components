package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What a multi-tab pane does: while the hand is down on it, where it went down and where it is kept, each move and the letting go told to the pane before its parts hear them. */
public record MultiTabPaneBehaviorModule() implements DomModule<MultiTabPaneBehaviorModule> {

    /** {@code new MultiTabPaneBehavior(el, pane)}; {@code press}, {@code at}, {@code drop()}. */
    public record MultiTabPaneBehavior() implements Exportable._Class<MultiTabPaneBehaviorModule> {}

    public static final MultiTabPaneBehaviorModule INSTANCE = new MultiTabPaneBehaviorModule();

    @Override
    public ImportsFor<MultiTabPaneBehaviorModule> imports() {
        return ImportsFor.<MultiTabPaneBehaviorModule>builder()
                .build();
    }

    @Override public ExportsOf<MultiTabPaneBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new MultiTabPaneBehavior())); }
}
