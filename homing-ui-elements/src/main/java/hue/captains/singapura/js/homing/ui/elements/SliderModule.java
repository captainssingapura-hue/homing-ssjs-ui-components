package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The slider: a value along a scale, named and read out - a label, the house's range (the browser's own slider, as each
 * style draws it) and a readout, in a row (owner, 23 Oct). Where it stands, and being in the hand, are its range's,
 * passed on; its keys are the range's own, the browser's.
 */
public record SliderModule() implements DomModule<SliderModule>, ImplFor<HouseControls.Slider> {

    /** A composed component, on a branch of its own: its label, its degree, its step, its readout's words, off. */
    public record Slider() implements BranchComponent<SliderModule> {
        @Override public String summary() { return "A value along a scale, named and read out: a label, the browser's own slider as each style draws it, a readout."; }
    }

    public static final SliderModule INSTANCE = new SliderModule();

    @Override
    public ImportsFor<SliderModule> imports() {
        return ImportsFor.<SliderModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LabelModule.Label()), LabelModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new RangeModule.Range()), RangeModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ReadoutModule.Readout()), ReadoutModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_slider(), new ElementStyles.el_slider_label(), new ElementStyles.el_slider_range(),
                        new ElementStyles.el_slider_readout()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SliderModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Slider()));
    }
}
