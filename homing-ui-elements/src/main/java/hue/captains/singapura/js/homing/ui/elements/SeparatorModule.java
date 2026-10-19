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

/** The separator: a simple component on a {@code <div>}, a line between the rows of a list or a menu; worn in its own look. */
public record SeparatorModule() implements DomModule<SeparatorModule>, ImplFor<HouseMarks.Separator> {

    /** An element component: {@code new Separator(el, {}, role)} on the element its companion makes. It shows nothing but itself, and offers nothing. */
    public record Separator() implements ElementComponent<SeparatorModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "A line between the rows of a list or a menu: one group ruled off from the next."; }
    }


    public static final SeparatorModule INSTANCE = new SeparatorModule();

    @Override
    public ImportsFor<SeparatorModule> imports() {
        return ImportsFor.<SeparatorModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SeparatorModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Separator()));
    }
}
