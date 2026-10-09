package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/** The scrim: a simple component on a {@code <div>}, the veil over the page behind a dialog, and a press; worn in its own look. */
public record ScrimModule() implements DomModule<ScrimModule>, ImplFor<HouseContainers.Scrim> {

    /** An element component: {@code new Scrim(el, {}, role)} on the element its companion makes; {@code setOnPress(handler)}. */
    public record Scrim() implements ElementComponent<ScrimModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "The veil over the page behind a dialog: the hand coming down on it is a press."; }
    }

    /** Its companion: {@code ScrimFactory.create(branch, name)} makes the element it is realized on. */
    public record ScrimFactory() implements Exportable._Constant<ScrimModule> {}

    public static final ScrimModule INSTANCE = new ScrimModule();

    @Override
    public ImportsFor<ScrimModule> imports() {
        return ImportsFor.<ScrimModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent(), new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ScrimBehaviorModule.ScrimBehavior()), ScrimBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ScrimModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Scrim(), new ScrimFactory()));
    }
}
