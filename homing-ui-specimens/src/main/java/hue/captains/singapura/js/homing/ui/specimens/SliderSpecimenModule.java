package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.elements.SliderModule;

import java.util.List;

/** {@code SliderSpecimen}: the house's slider in action - dragged, pressed on its rail, moved by the keys handed on, switched off. */
public record SliderSpecimenModule() implements DomModule<SliderSpecimenModule> {

    public static final SliderSpecimenModule INSTANCE = new SliderSpecimenModule();

    public record SliderSpecimen() implements BranchComponent<SliderSpecimenModule>, NeedKeyboard {
        @Override public String summary() { return "The house's slider in action: a value on a scale with ticks, figures and a detent, read out as it moves - dragged, pressed on its rail, moved by the keys handed on - and switched off."; }
        @Override public List<KeyBinding> keys() { return SliderModule.Slider.KEYS; }
    }

    @Override
    public ImportsFor<SliderSpecimenModule> imports() {
        return ImportsFor.<SliderSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new SliderModule.SliderBuilder()), SliderModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SliderSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SliderSpecimen())); }
}
