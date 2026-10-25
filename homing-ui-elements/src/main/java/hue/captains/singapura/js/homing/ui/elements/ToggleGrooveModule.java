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

/** The toggle groove: a simple component on a {@code <span>}, worn in its own look. The pill a switch's knob slides in, in the accent when the switch is on. */
public record ToggleGrooveModule() implements DomModule<ToggleGrooveModule>, ImplFor<HouseMarks.ToggleGroove> {

    /** An element component: {@code new ToggleGroove(branch.elementMaker("groove"), { checked: true }, ROLE.SPAN)} on the element its owner's branch makes. */
    public record ToggleGroove() implements ElementComponent<ToggleGrooveModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "The pill a switch's knob slides in, in the accent when the switch is on."; }
    }


    public static final ToggleGrooveModule INSTANCE = new ToggleGrooveModule();

    @Override
    public ImportsFor<ToggleGrooveModule> imports() {
        return ImportsFor.<ToggleGrooveModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ToggleGrooveModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ToggleGroove()));
    }
}
