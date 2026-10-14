package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

import java.util.List;

/** A scale's tick. A simple component on its own {@code <div>}, a mark of the house. */
public record TickModule() implements DomModule<TickModule>, ImplFor<HouseMarks.Tick> {

    public record Tick() implements ElementComponent<TickModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "A scale's tick."; }
    }

    public record TickFactory() implements Exportable._Constant<TickModule> {}

    public static final TickModule INSTANCE = new TickModule();

    @Override
    public ImportsFor<TickModule> imports() {
        return ImportsFor.<TickModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent(), new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TickModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Tick(), new TickFactory()));
    }
}
