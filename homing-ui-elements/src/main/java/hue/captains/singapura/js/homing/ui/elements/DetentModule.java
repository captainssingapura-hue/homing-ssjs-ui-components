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

/** A notch a value may snap to, shown while what rests on it is in the hand. A simple component on its own {@code <div>}, a mark of the house. */
public record DetentModule() implements DomModule<DetentModule>, ImplFor<HouseMarks.Detent> {

    public record Detent() implements ElementComponent<DetentModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "A notch a value may snap to, shown while what rests on it is in the hand."; }
    }

    public record DetentFactory() implements Exportable._Constant<DetentModule> {}

    public static final DetentModule INSTANCE = new DetentModule();

    @Override
    public ImportsFor<DetentModule> imports() {
        return ImportsFor.<DetentModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.STATE()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent(), new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<DetentModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Detent(), new DetentFactory()));
    }
}
