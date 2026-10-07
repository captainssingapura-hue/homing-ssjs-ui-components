package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;

import java.util.List;

/** {@code ButtonSpecimen}: one of the house's six buttons in action - pressed, switched off - at its size and, coloured, its colour's extent. */
public record ButtonSpecimenModule() implements DomModule<ButtonSpecimenModule> {

    public static final ButtonSpecimenModule INSTANCE = new ButtonSpecimenModule();

    public record ButtonSpecimen() implements BranchComponent<ButtonSpecimenModule> {
        @Override public String summary() { return "One of the house's six buttons, as its leaf says, in action: pressed and counted, switched off and inert; its size, and a coloured one's colour, set by number."; }
    }

    @Override
    public ImportsFor<ButtonSpecimenModule> imports() {
        return ImportsFor.<ButtonSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<ButtonSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ButtonSpecimen())); }
}
