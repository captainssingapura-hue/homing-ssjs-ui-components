package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What a bar of tabs does: where the hand goes down on it kept, and while one of its chips is held, each move of the hand told to the bar - until the chip is let go. */
public record TabStripBehaviorModule() implements DomModule<TabStripBehaviorModule> {

    /** {@code new TabStripBehavior(el, strip)}; {@code grab()}, {@code drop()}. */
    public record TabStripBehavior() implements Exportable._Class<TabStripBehaviorModule> {}

    public static final TabStripBehaviorModule INSTANCE = new TabStripBehaviorModule();

    @Override
    public ImportsFor<TabStripBehaviorModule> imports() {
        return ImportsFor.<TabStripBehaviorModule>builder()
                .build();
    }

    @Override public ExportsOf<TabStripBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TabStripBehavior())); }
}
