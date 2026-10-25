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

/** The select: a simple component on a {@code <select>}, riding on the browser's own; worn in its own look. A value picked from a list that drops down: the browser's own. */
public record SelectModule() implements DomModule<SelectModule>, ImplFor<HouseControls.Select> {

    /** An element component: {@code new Select(make, new FixedSelect(), place?)}; {@code onChange(StateChosenOption, listener)}; {@code options}, {@code value}, {@code chosen}, {@code enabled}, {@code size}. */
    public record Select() implements ElementComponent<SelectModule> {
        @Override public String tag() { return "select"; }
        @Override public String summary() { return "A value picked from a list that drops down: the browser's own."; }
    }


    public static final SelectModule INSTANCE = new SelectModule();

    @Override
    public ImportsFor<SelectModule> imports() {
        return ImportsFor.<SelectModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new InputBehaviorsModule.PickBehavior()), InputBehaviorsModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SelectModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Select()));
    }
}
