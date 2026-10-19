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

/** The line where two regions of a layout meet: lying or upright, in the hand while its handle moves it. A simple component on its own {@code <div>}, a mark of the house. */
public record BoundaryLineModule() implements DomModule<BoundaryLineModule>, ImplFor<HouseMarks.BoundaryLine> {

    public record BoundaryLine() implements ElementComponent<BoundaryLineModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "The line where two regions of a layout meet: lying or upright, in the hand while its handle moves it."; }
    }


    public static final BoundaryLineModule INSTANCE = new BoundaryLineModule();

    @Override
    public ImportsFor<BoundaryLineModule> imports() {
        return ImportsFor.<BoundaryLineModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<BoundaryLineModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new BoundaryLine()));
    }
}
