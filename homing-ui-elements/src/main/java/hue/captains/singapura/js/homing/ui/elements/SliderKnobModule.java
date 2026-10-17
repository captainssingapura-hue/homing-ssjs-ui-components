package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** The slider knob: a simple component on a {@code <div>}, worn in its own look. What is grabbed to move a slider's value along its groove; upright it is a cap. */
public record SliderKnobModule() implements DomModule<SliderKnobModule>, ImplFor<HouseControls.SliderKnob> {

    /** An element component: {@code new SliderKnob(branch.elementMaker("knob"), { orientation: "vertical" }, ROLE.THUMB)} on the element its owner's branch makes. */
    public record SliderKnob() implements ElementComponent<SliderKnobModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "What is grabbed to move a slider's value along its groove; upright it is a cap."; }
    }

    /** Its companion: {@code SliderKnobFactory.create(branch, name)} makes the element it is realized on. */
    public record SliderKnobFactory() implements Exportable._Constant<SliderKnobModule> {}

    public static final SliderKnobModule INSTANCE = new SliderKnobModule();

    @Override
    public ImportsFor<SliderKnobModule> imports() {
        return ImportsFor.<SliderKnobModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.STATE()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent(), new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SliderKnobModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SliderKnob(), new SliderKnobFactory()));
    }
}
