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

/** The slider groove: a simple component on a {@code <div>}, worn in its own look. The line a slider's value is set along: the whole of its range. */
public record SliderGrooveModule() implements DomModule<SliderGrooveModule>, ImplFor<HouseMarks.SliderGroove> {

    /** An element component: {@code new SliderGroove(branch.elementMaker("track"), {}, ROLE.SPAN)} on the element its owner's branch makes. */
    public record SliderGroove() implements ElementComponent<SliderGrooveModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "The line a slider's value is set along: the whole of its range."; }
    }


    public static final SliderGrooveModule INSTANCE = new SliderGrooveModule();

    @Override
    public ImportsFor<SliderGrooveModule> imports() {
        return ImportsFor.<SliderGrooveModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SliderGrooveModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SliderGroove()));
    }
}
