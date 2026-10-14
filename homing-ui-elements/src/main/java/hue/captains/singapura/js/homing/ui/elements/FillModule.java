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

/** The fill: a simple component on a {@code <div>}, worn in its own look. The part of the range the value has reached. */
public record FillModule() implements DomModule<FillModule>, ImplFor<HouseMarks.Fill> {

    /** An element component: {@code new Fill(branch.elementMaker("fill"), {}, ROLE.LEVEL)} on the element its owner's branch makes. */
    public record Fill() implements ElementComponent<FillModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "The part of the range the value has reached."; }
    }

    /** Its companion: {@code FillFactory.create(branch, name)} makes the element it is realized on. */
    public record FillFactory() implements Exportable._Constant<FillModule> {}

    public static final FillModule INSTANCE = new FillModule();

    @Override
    public ImportsFor<FillModule> imports() {
        return ImportsFor.<FillModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent(), new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<FillModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Fill(), new FillFactory()));
    }
}
