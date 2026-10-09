package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/** What a way somewhere does: a click on it is its click, dressed from the DOM's and fired to its handler - the link's and the crumb's. */
public record LinkBehaviorModule() implements DomModule<LinkBehaviorModule> {

    /** {@code new LinkBehavior(el, link)}. */
    public record LinkBehavior() implements Exportable._Class<LinkBehaviorModule> {}

    public static final LinkBehaviorModule INSTANCE = new LinkBehaviorModule();

    @Override
    public ImportsFor<LinkBehaviorModule> imports() {
        return ImportsFor.<LinkBehaviorModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .build();
    }

    @Override public ExportsOf<LinkBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new LinkBehavior())); }
}
