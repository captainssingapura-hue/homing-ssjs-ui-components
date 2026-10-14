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

/** The range: a simple component on a {@code <input>}, riding on the browser's own; worn in its own look. A value picked along a scale: the browser's own slider. */
public record RangeModule() implements DomModule<RangeModule>, ImplFor<HouseControls.Range> {

    /** An element component: {@code new Range(RangeFactory.create(branch, "name"), { min, max, step, value, enabled }, role)} on the element its companion makes; {@code setOnInput(handler)}, {@code setOnCommit(handler)}; {@code min}, {@code max}, {@code step}, {@code value}, {@code enabled}. */
    public record Range() implements ElementComponent<RangeModule> {
        @Override public String tag() { return "input"; }
        @Override public String summary() { return "A value picked along a scale: the browser's own slider."; }
    }

    /** Its companion: {@code RangeFactory.create(branch, name)} makes the element it is realized on. */
    public record RangeFactory() implements Exportable._Constant<RangeModule> {}

    public static final RangeModule INSTANCE = new RangeModule();

    @Override
    public ImportsFor<RangeModule> imports() {
        return ImportsFor.<RangeModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.STATE(), new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent(), new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new InputBehaviorsModule.EditBehavior()), InputBehaviorsModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<RangeModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Range(), new RangeFactory()));
    }
}
