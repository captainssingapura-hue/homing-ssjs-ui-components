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

/** The grip: a simple component on a {@code <div>}, worn in its own look. What the user takes hold of to move the thing it is on. */
public record GripModule() implements DomModule<GripModule>, ImplFor<HouseControls.Grip> {

    /** An element component: {@code new Grip(branch.elementMaker("grip"), {}, ROLE.STANDALONE)} on the element its owner's branch makes. */
    public record Grip() implements ElementComponent<GripModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "What the user takes hold of to move the thing it is on."; }
    }

    /** Its companion: {@code GripFactory.create(branch, name)} makes the element it is realized on. */
    public record GripFactory() implements Exportable._Constant<GripModule> {}

    public static final GripModule INSTANCE = new GripModule();

    @Override
    public ImportsFor<GripModule> imports() {
        return ImportsFor.<GripModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.STATE()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent(), new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<GripModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Grip(), new GripFactory()));
    }
}
