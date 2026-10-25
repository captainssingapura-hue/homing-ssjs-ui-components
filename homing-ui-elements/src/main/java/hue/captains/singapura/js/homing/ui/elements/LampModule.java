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

/** A light that says how something is: quiet, lit or broken. A simple component on its own {@code <div>}, a mark of the house. */
public record LampModule() implements DomModule<LampModule>, ImplFor<HouseMarks.Lamp> {

    public record Lamp() implements ElementComponent<LampModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "A light that says how something is: quiet, lit or broken."; }
    }


    public static final LampModule INSTANCE = new LampModule();

    @Override
    public ImportsFor<LampModule> imports() {
        return ImportsFor.<LampModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<LampModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Lamp()));
    }
}
