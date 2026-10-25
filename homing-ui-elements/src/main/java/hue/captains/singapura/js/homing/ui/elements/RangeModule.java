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

/** The range: a simple component on a {@code <input>}, wrapping the browser's own slider; worn in its own look. A degree picked along it. */
public record RangeModule() implements DomModule<RangeModule>, ImplFor<HouseControls.Range> {

    /** An element component: {@code new Range(make, new FixedRange(), place?)}; {@code onChange(StateDegree, listener)}, {@code onChange(StateDragging, listener)}; {@code degree}, {@code step}, {@code dragging}, {@code enabled}. */
    public record Range() implements ElementComponent<RangeModule> {
        @Override public String tag() { return "input"; }
        @Override public String summary() { return "A degree picked along the browser's own slider."; }
    }


    public static final RangeModule INSTANCE = new RangeModule();

    @Override
    public ImportsFor<RangeModule> imports() {
        return ImportsFor.<RangeModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new InputBehaviorsModule.SlideBehavior()), InputBehaviorsModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<RangeModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Range()));
    }
}
