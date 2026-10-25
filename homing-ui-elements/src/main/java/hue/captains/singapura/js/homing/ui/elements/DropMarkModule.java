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

/** Where a dragged thing would land. A simple component on its own {@code <div>}, a mark of the house. */
public record DropMarkModule() implements DomModule<DropMarkModule>, ImplFor<HouseMarks.DropMark> {

    public record DropMark() implements ElementComponent<DropMarkModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "Where a dragged thing would land."; }
    }


    public static final DropMarkModule INSTANCE = new DropMarkModule();

    @Override
    public ImportsFor<DropMarkModule> imports() {
        return ImportsFor.<DropMarkModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<DropMarkModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new DropMark()));
    }
}
