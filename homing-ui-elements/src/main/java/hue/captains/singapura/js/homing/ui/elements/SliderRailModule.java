package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

import java.util.List;

/** The slider rail: a simple component on a {@code <div>}, worn in its own look. The stretch a slider's value is set along, the hand taken anywhere on it. */
public record SliderRailModule() implements DomModule<SliderRailModule>, ImplFor<HouseMarks.SliderRail> {

    /** An element component: {@code new SliderRail(branch.elementMaker("rail"), {}, ROLE.EDITOR)} on the element its owner's branch makes. */
    public record SliderRail() implements ElementComponent<SliderRailModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "The stretch a slider's value is set along, the hand taken anywhere on it."; }
    }

    /** Its companion: {@code SliderRailFactory.create(branch, name)} makes the element it is realized on. */
    public record SliderRailFactory() implements Exportable._Constant<SliderRailModule> {}

    public static final SliderRailModule INSTANCE = new SliderRailModule();

    @Override
    public ImportsFor<SliderRailModule> imports() {
        return ImportsFor.<SliderRailModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.STATE()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent(), new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SliderRailModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SliderRail(), new SliderRailFactory()));
    }
}
