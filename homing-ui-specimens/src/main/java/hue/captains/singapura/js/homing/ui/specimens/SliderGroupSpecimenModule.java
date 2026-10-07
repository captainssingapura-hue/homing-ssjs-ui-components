package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.SliderGroupModule;
import hue.captains.singapura.js.homing.ui.elements.SliderModule;

import java.util.List;

/** {@code SliderGroupSpecimen}: the house's slider group in action - three faders holding the keys as one, Tab between them. */
public record SliderGroupSpecimenModule() implements DomModule<SliderGroupSpecimenModule> {

    public static final SliderGroupSpecimenModule INSTANCE = new SliderGroupSpecimenModule();

    public record SliderGroupSpecimen() implements BranchComponent<SliderGroupSpecimenModule>, NeedKeyboard {
        @Override public String summary() { return "The house's slider group in action: three faders under one heading, holding the keys as one - Tab between them, the arrows on the current one - each value set said."; }
        @Override public List<KeyBinding> keys() { return new SliderGroupModule.SliderGroup().keys(); }
    }

    @Override
    public ImportsFor<SliderGroupSpecimenModule> imports() {
        return ImportsFor.<SliderGroupSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new SliderGroupModule.SliderGroupBuilder()), SliderGroupModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SliderModule.SliderBuilder()), SliderModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SliderGroupSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SliderGroupSpecimen())); }
}
