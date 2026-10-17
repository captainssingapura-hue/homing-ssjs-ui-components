package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** The toggle knob: a simple component on a {@code <span>}, worn in its own look. What shows which way a switch stands, sliding in its groove. */
public record ToggleKnobModule() implements DomModule<ToggleKnobModule>, ImplFor<HouseControls.ToggleKnob> {

    /** An element component: {@code new ToggleKnob(branch.elementMaker("knob"), {}, ROLE.THUMB)} on the element its owner's branch makes. */
    public record ToggleKnob() implements ElementComponent<ToggleKnobModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "What shows which way a switch stands, sliding in its groove."; }
    }

    /** Its companion: {@code ToggleKnobFactory.create(branch, name)} makes the element it is realized on. */
    public record ToggleKnobFactory() implements Exportable._Constant<ToggleKnobModule> {}

    public static final ToggleKnobModule INSTANCE = new ToggleKnobModule();

    @Override
    public ImportsFor<ToggleKnobModule> imports() {
        return ImportsFor.<ToggleKnobModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent(), new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ToggleKnobModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ToggleKnob(), new ToggleKnobFactory()));
    }
}
